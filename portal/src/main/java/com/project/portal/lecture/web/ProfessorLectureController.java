package com.project.portal.lecture.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.project.portal.course.service.CourseVO;
import com.project.portal.course.service.impl.CourseServiceImpl;
import com.project.portal.lecture.service.LectureVO;
import com.project.portal.lecture.service.VideoVO;
import com.project.portal.lecture.service.impl.ProfessorLectureServiceImpl;

@Controller
public class ProfessorLectureController {
	
	@Autowired CourseServiceImpl courseService;
	@Autowired ProfessorLectureServiceImpl service;
	private String courseCode = "SSPY0001";
	
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	@RequestMapping("professor/eclass/lectureList")
	public String lectureList(Model model, CourseVO vo) {
		vo.setCourseCode("SSPY0001");
		List<LectureVO> lectureList = service.getLectureList(vo);
		model.addAttribute("lectureList", lectureList);
		return "professor/eclass/lecture/lectureList";
	}
	
	@RequestMapping("professor/eclass/insertLecture")
	public String insertLecture(CourseVO vo, Model model) {
		vo.setCourseCode("SSPY0001");
		// 주차 정보
		vo = courseService.getWeeklyInfo(vo);
		System.out.println(vo);
		model.addAttribute("courseInfo", vo);
		return "professor/eclass/lecture/insertLecture";
	}
	
	@PostMapping("professor/eclass/insertLecture")
	public String loadedData(LectureVO lecture, MultipartFile file, VideoVO video) throws IllegalStateException, IOException {
		
		// 비디오 테이블에 등록하기
		video.setVideoName(file.getOriginalFilename());
		video.setVideoStoredName(UUID.randomUUID().toString().replaceAll("-", "") + file.getOriginalFilename());
		video.setVideoExtension(file.getContentType().substring(5));
		video.setVideoFilePath(uploadPath + "/video/" + courseCode + "/" + video.getVideoStoredName());
		video.setProfessorId(220001);
		service.uploadVideo(video);
		
		// 비디오 실제 파일 업로드
		File fileUpload = new File(video.getVideoFilePath());
		// 부모 디렉토리 생성하는 부분
		fileUpload.getParentFile().mkdirs();
		file.transferTo(fileUpload);
		
		// 비디오 코드 받아서 강의 영상 자료 정보 테이블에 등록해오기
		lecture.setVideoCode(video.getVideoCode());
		System.out.println(lecture);
		service.insertLecture(lecture);
		
		return "redirect:/professor/eclass/lectureList";
	}
	
	@GetMapping("/video/download/{videoCode}")
	public ResponseEntity<Object> download(@PathVariable String videoCode) {
		String path = service.getVideo(videoCode).getVideoFilePath();
		try {
			Path filePath = Paths.get(path);
			Resource resource = new InputStreamResource(Files.newInputStream(filePath));
			
			File file = new File(path);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getName()).build());
			
			return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		}
	}

}
