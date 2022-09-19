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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.portal.bachelor.service.BachelorNoticeFileVO;
import com.project.portal.bachelor.service.BachelorNoticeVO;
import com.project.portal.bachelor.service.impl.BachelorNoticeServiceImpl;
import com.project.portal.lecture.service.VideoVO;
import com.project.portal.lecture.service.impl.ProfessorLectureServiceImpl;
import com.project.portal.report.service.ReportFileVO;
import com.project.portal.report.service.impl.ProfessorReportServiceImpl;
import com.project.portal.studynotice.service.StudyNoticeFileVO;
import com.project.portal.studynotice.service.impl.StudyNoticeServiceImpl;

// 작성자: 권유진, 박근형 

@Controller
public class FileController {
	
	@Autowired
	ProfessorLectureServiceImpl lectureService;
	@Autowired
	ProfessorReportServiceImpl reportService;
	@Autowired
	StudyNoticeServiceImpl noticeService;
	@Autowired
	BachelorNoticeServiceImpl bnoticeService;
	
	@GetMapping("/video/download/{videoCode}")
	public ResponseEntity<Object> videoDownload(@PathVariable String videoCode) {
		VideoVO video = lectureService.getVideo(videoCode);
		return setResponseEntity(video.getVideoFilePath(), video.getVideoName());
	} 
	
	@GetMapping("/report/download/{reportFileCode}/{userCode}")
	public ResponseEntity<Object> fileDownload(@PathVariable String reportFileCode, 
												@PathVariable String userCode) {
		ReportFileVO report = reportService.getFile(reportFileCode, userCode);
		return setResponseEntity(report.getReportFilePath(), report.getReportFileName());			
	}
	
	@GetMapping("/courseNotice/download/{fileName}")
	public ResponseEntity<Object> courseNoticefileDownload(@PathVariable String fileName) {
		StudyNoticeFileVO fileVO = noticeService.filedownload(fileName);
		return setResponseEntity(fileVO.getFileUrl(), fileVO.getFileName());
	}
	
	@GetMapping("/notice/download/{noticeFileCode}")
	public ResponseEntity<Object> noticeFileDownload(@PathVariable String noticeFileCode) {
		BachelorNoticeVO vo = new BachelorNoticeVO();
		vo.setNoticeFileCode(noticeFileCode);
		BachelorNoticeFileVO file = bnoticeService.getFile(vo);
		return setResponseEntity(file.getNoticeFilePath(), file.getNoticeFileName());
	}

	public ResponseEntity<Object> setResponseEntity(String path, String downloadName) {
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
		headers.setContentDisposition(ContentDisposition.builder("attachment").filename(downloadName).build());
		return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);
	}
		
	
}
