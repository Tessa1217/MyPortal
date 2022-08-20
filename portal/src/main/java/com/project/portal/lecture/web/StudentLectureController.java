package com.project.portal.lecture.web;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.project.portal.course.service.CourseVO;
import com.project.portal.lecture.service.LectureVO;
import com.project.portal.lecture.service.impl.StudentLectureServiceImpl;

@Controller
public class StudentLectureController {
	
	@Autowired StudentLectureServiceImpl service;
	
	@RequestMapping("student/lectureList")
	public String lectureList(CourseVO vo, Model model) {
		vo.setCourseCode("SSPY0001");
		model.addAttribute("lectureList", service.getLectureList(vo));
		return "student/eclass/lecture/lectureList";
	}
	
	@RequestMapping("student/watchLecture")
	public String watchLecture(LectureVO vo, Model model) {
		vo = service.getLecture(vo);
//		MultipartFile video = new MultipartFile();
		model.addAttribute("lecture", vo);
//		model.addAttribute("video", video);
//		System.out.println(video.length());
		return "student/eclass/lecture/watchLecture";
	}
}
