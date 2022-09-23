package com.project.portal.exam.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.course.service.CourseVO;
import com.project.portal.exam.service.CourseExamVO;
import com.project.portal.exam.service.ExamResultVO;
import com.project.portal.exam.service.ExamScoreVO;
import com.project.portal.exam.service.ExamTakeVO;
import com.project.portal.exam.service.ExamVO;
import com.project.portal.exam.service.StudentExamService;

@Controller
@EnableScheduling
public class StudentExamController {
	
	@Autowired
	StudentExamService service;
	
	@ModelAttribute("course")
	public CourseVO course(HttpSession session) {
		CourseVO course = new CourseVO();
		course.setCourseCode((String) session.getAttribute("courseCode"));
		return course;
	}
	
	@RequestMapping("/student/eclass/examList")
	public String studentExamInfo(Model model, HttpSession session) {
		List<ExamScoreVO> examList = service.getExamInfo((int) session.getAttribute("id"), 
														(CourseVO) model.getAttribute("course"));
		model.addAttribute("examList", examList);
		return "student/eclass/exam/examList";
	}
	
	@RequestMapping("/student/eclass/examTake")
	public String studentExamTake(ExamVO vo, Model model, HttpSession session) {
		List<CourseExamVO> studentExam = service.getStudentExam(vo);
		model.addAttribute("examInfo", vo);
		model.addAttribute("studentExam", studentExam);
		return "student/eclass/exam/examTake";
	}
	
	@PostMapping("/student/eclass/examTake")
	public String studentExamTaken(@RequestBody ExamTakeVO vo) {
		service.insertExamResult(vo);
		return "redirect:/student/eclass/examList";
	}
	
	@RequestMapping("/student/eclass/examResult")
	public String examResult(ExamVO vo, Model model, HttpSession session) {
		List<ExamResultVO> studentExam = service.getExamResult((int) session.getAttribute("id"), vo);
		System.out.println("시험 결과");
		System.out.println(studentExam.get(0));
		model.addAttribute("studentExam", studentExam);
		return "student/eclass/exam/studentExamResult";
	}
	
}
