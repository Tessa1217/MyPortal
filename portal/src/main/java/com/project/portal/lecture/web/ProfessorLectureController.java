package com.project.portal.lecture.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	@RequestMapping("professor/lectureList")
	public String lectureList(Model model, CourseVO vo) {
		vo.setCourseCode("SSPY0001");
		List<LectureVO> lectureList = service.getLectureList(vo);
		System.out.println(lectureList);
		model.addAttribute("lectureList", lectureList);
		return "professor/eclass/lectureList";
	}
	
	@RequestMapping("professor/insertLecture")
	public String insertLecture(CourseVO vo, Model model) {
		vo.setCourseCode("SSPY0001");
		// 주차 정보
		vo = courseService.getWeeklyInfo(vo);
		System.out.println(vo);
		model.addAttribute("courseInfo", vo);
		return "professor/eclass/insertLecture";
	}
	
	@PostMapping("professor/insertLecture")
	public String loadedData(LectureVO lecture, MultipartFile file, VideoVO video) throws IllegalStateException, IOException {
		
		// 비디오 테이블에 등록하기
		video.setVideoName(file.getOriginalFilename());
		video.setVideoStoredName(UUID.randomUUID().toString().replaceAll("-", "") + file.getOriginalFilename());
		video.setVideoExtension(file.getContentType().substring(5));
		video.setVideoFilePath(uploadPath + "/" + video.getVideoStoredName());
		video.setProfessorId(220001);
		service.uploadVideo(video);
		
		// 비디오 실제 파일 업로드
		File fileUpload = new File(video.getVideoFilePath());
		file.transferTo(fileUpload);
		
		// 비디오 코드 받아서 강의 영상 자료 정보 테이블에 등록해오기
		lecture.setVideoCode(video.getVideoCode());
		service.insertLecture(lecture);
		
		return "redirect:/professor/lectureList";
	}

}
