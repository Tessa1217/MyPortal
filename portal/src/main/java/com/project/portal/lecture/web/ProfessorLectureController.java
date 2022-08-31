package com.project.portal.lecture.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.project.portal.course.service.CourseVO;
import com.project.portal.course.service.impl.CourseServiceImpl;
import com.project.portal.lecture.service.LectureVO;
import com.project.portal.lecture.service.VideoVO;
import com.project.portal.lecture.service.impl.ProfessorLectureServiceImpl;

// 작성자: 권유진 

@Controller
public class ProfessorLectureController {
	
	Logger log = LoggerFactory.getLogger(ProfessorLectureController.class);
	
	@Autowired CourseServiceImpl courseService;
	@Autowired ProfessorLectureServiceImpl service;
	
	// 강의 정보 
	@ModelAttribute("courseInfo")
	public CourseVO course(HttpSession session) {
		CourseVO course = new CourseVO();
		course.setCourseCode((String) session.getAttribute("courseCode"));
		return courseService.getWeeklyInfo(course);
	}
	
	// 파일 업로드하는 상위 디렉토리
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	// 강의 리스트 페이지 이동 
	@RequestMapping("professor/eclass/lectureList")
	public String lectureList(Model model) {
		List<LectureVO> lectureList = service.getLectureList((CourseVO) model.getAttribute("courseInfo"));
		model.addAttribute("lectureList", lectureList);
		return "professor/eclass/lecture/lectureList";
	}
	
	// 강의 등록 페이지
	@RequestMapping("professor/eclass/insertLecture")
	public String insertLecture(CourseVO vo, Model model) {
		return "professor/eclass/lecture/insertLecture";
	}
	
	// 강의 등록
	@PostMapping("professor/eclass/insertLecture")
	public String loadedData(LectureVO lecture, MultipartFile file, VideoVO video, Model model) throws IllegalStateException, IOException {
		
		// 비디오 파일 테이블에 등록하기
		CourseVO course = (CourseVO) model.getAttribute("courseInfo");
		video.setVideoName(file.getOriginalFilename());
		video.setVideoStoredName(UUID.randomUUID().toString().replaceAll("-", "") + file.getOriginalFilename());
		video.setVideoExtension(file.getContentType().substring(5));
		video.setVideoFilePath(uploadPath + "/video/" + 
		course.getCourseYear() + "/" + course.getCourseSemester() + "/" + 
		course.getCourseCode() + "/" + video.getVideoStoredName());
		video.setProfessorId(course.getProfessorId());
		service.uploadVideo(video);
		
		// 비디오 실제 파일 업로드
		File fileUpload = new File(video.getVideoFilePath());
		
		// 부모 디렉토리가 존재하지 않을 경우 생성하여 오류 막는 부분
		fileUpload.getParentFile().mkdirs();
		file.transferTo(fileUpload);
		
		// 비디오 코드 받아서 강의 영상 자료 정보 테이블에 등록해오기
		lecture.setVideoCode(video.getVideoCode());
		service.insertLecture(lecture);
		
		return "redirect:/professor/eclass/lectureList";
	}

}
