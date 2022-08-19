package com.project.portal.exam.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.course.service.CourseVO;
import com.project.portal.exam.service.CourseExamVO;
import com.project.portal.exam.service.ExamVO;
import com.project.portal.exam.service.StudentExamService;

@Controller
public class StudentExamController {
	
	@Autowired
	StudentExamService service;
	
	@RequestMapping("/student/exam")
	public String studentExamInfo(CourseVO vo, Model model) {
		vo.setCourseCode("SSPY0001");
		model.addAttribute("examList", service.getExamInfo(vo));
		return "student/eclass/examList";
	}
	
	@RequestMapping("/student/examTake")
	public String studentExamTake(ExamVO vo, Model model) {
		vo.setExamCode("MSSPY0001222");
		List<CourseExamVO> studentExam = service.getStudentExam(vo);
		System.out.println(studentExam);
		model.addAttribute("studentExam", studentExam);
		return "student/eclass/examTake";
	}

}
