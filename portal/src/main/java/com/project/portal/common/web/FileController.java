package com.project.portal.common.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	// 강의 영상 파일 
	@GetMapping("/video/download/{videoCode}")
	public ResponseEntity<Object> videoDownload(@PathVariable String videoCode) {
		VideoVO video = lectureService.getVideo(videoCode);
		return setResponseEntity(video.getVideoFilePath(), video.getVideoName());
	} 
	
	// 과제 파일 (교수, 학생)
	@GetMapping("/report/download/{reportFileCode}/{userCode}")
	public ResponseEntity<Object> fileDownload(@PathVariable String reportFileCode, 
												@PathVariable String userCode) {
		ReportFileVO report = reportService.getFile(reportFileCode, userCode);
		return setResponseEntity(report.getReportFilePath(), report.getReportFileName());			
	}
	
	// 강의 공지사항
	@GetMapping("/courseNotice/download/{fileName}")
	public ResponseEntity<Object> courseNoticefileDownload(@PathVariable String fileName) {
		StudyNoticeFileVO fileVO = noticeService.filedownload(fileName);
		return setResponseEntity(fileVO.getFileUrl(), fileVO.getFileName());
	}
	
	// 학사 공지사항
	@GetMapping("/notice/download/{noticeFileCode}")
	public ResponseEntity<Object> noticeFileDownload(@PathVariable String noticeFileCode) {
		BachelorNoticeVO vo = new BachelorNoticeVO();
		vo.setNoticeFileCode(noticeFileCode);
		BachelorNoticeFileVO file = bnoticeService.getFile(vo);
		return setResponseEntity(file.getNoticeFilePath(), file.getNoticeFileName());
	}
	
	// 파일 다운로드 경로
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	// 사용자 프로필 이미지
	@GetMapping(value = "/download/{fileName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Object> downloadFile(@RequestHeader("User-Agent") String userAgent, 
												@PathVariable String fileName) 
												throws UnsupportedEncodingException {
		
		String path = uploadPath + "/images/" + fileName;
		return setResponseEntity(path, "user_profile");
	}
	
	// ResponseEntity 반환하는 메서드
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
