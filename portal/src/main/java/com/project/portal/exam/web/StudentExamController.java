package com.project.portal.exam.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.course.service.CourseVO;
import com.project.portal.exam.service.CourseExamVO;
import com.project.portal.exam.service.ExamScoreVO;
import com.project.portal.exam.service.ExamVO;
import com.project.portal.exam.service.StudentExamService;

@Controller
public class StudentExamController {
	
	@Autowired
	StudentExamService service;
	
	@RequestMapping("/student/exam")
	public String studentExamInfo(CourseVO course, Model model) {
		course.setCourseCode("SSPY0001");
		// 학번 (원래는 세션값으로 들어옴)
		int studentId = 22000001;
		List<ExamScoreVO> examList = service.getExamInfo(studentId, course);
		System.out.println(examList);
		System.out.println(examList.size());
		model.addAttribute("examList", examList);
		return "student/eclass/examList";
	}
	
	@RequestMapping("/student/examTake")
	public String studentExamTake(ExamVO vo, Model model) {
		System.out.println(vo);
		List<CourseExamVO> studentExam = service.getStudentExam(vo);
		System.out.println(studentExam);
		model.addAttribute("studentExam", studentExam);
		return "student/eclass/examTake";
	}

}
