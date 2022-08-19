package com.project.portal.lecture.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.course.service.CourseVO;
import com.project.portal.lecture.service.impl.StudentLectureServiceImpl;

@Controller
public class StudentLectureController {
	
	@Autowired StudentLectureServiceImpl service;
	
	@RequestMapping("student/lectureList")
	public String lectureList(CourseVO vo, Model model) {
		vo.setCourseCode("SSPY0001");
		model.addAttribute("lectureList", service.getLectureList(vo));
		return "student/eclass/lectureList";
	}
	
	@RequestMapping("student/watchLecture")
	public String watchLecture() {
		return "student/eclass/watchLecture";
	}
}
