package com.project.portal.common.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.portal.lecture.service.impl.ProfessorLectureServiceImpl;
import com.project.portal.report.service.impl.ProfessorReportServiceImpl;

@Controller
public class FileController {
	
	@Autowired
	ProfessorLectureServiceImpl lectureService;
	@Autowired
	ProfessorReportServiceImpl reportService;
	
	@GetMapping("/video/download/{videoCode}")
	public ResponseEntity<Object> videoDownload(@PathVariable String videoCode) {
		String path = lectureService.getVideo(videoCode).getVideoFilePath();
		return setResponseEntity(path);
	} 
	
	@GetMapping("/report/download/{reportFileCode}")
	public ResponseEntity<Object> fielDownload(@PathVariable String reportFileCode) {
		String path = reportService.getFile(reportFileCode).getReportFilePath();
		return setResponseEntity(path);			
	}

	public ResponseEntity<Object> setResponseEntity(String path) {
		Path filePath = Paths.get(path);
		Resource resource = null;
		try {
			resource = new InputStreamResource(Files.newInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		File file = new File(path);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Length", String.valueOf(file.length()));
		headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getName()).build());
		return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);
	}
		
	
}
