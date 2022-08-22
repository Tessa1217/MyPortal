package com.project.portal.lecture.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.portal.course.service.CourseVO;
import com.project.portal.lecture.service.LectureVO;
import com.project.portal.lecture.service.StudentLectureVO;
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
		model.addAttribute("lecture", vo);
		return "student/eclass/lecture/watchLecture";
	}
	
	@PostMapping("student/watchLecture")
	@ResponseBody
	public String watchRecord(@RequestBody StudentLectureVO vo) {
		if (vo.getCmd().equals("insert")) {
			System.out.println(vo);
			service.insertLectureRecord(vo);
		} 
		if (vo.getCmd().equals("update")) {
			System.out.println("update");
			service.updateLectureRecord(vo);
		}
		return "success";
	}
}
