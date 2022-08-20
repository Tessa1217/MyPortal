package com.project.portal.exam.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.portal.course.service.CourseVO;
import com.project.portal.exam.service.CourseExamVO;
import com.project.portal.exam.service.ExamScoreVO;
import com.project.portal.exam.service.ExamVO;
import com.project.portal.exam.service.StudentExamService;
import com.project.portal.exam.service.StudentExamVO;

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
		model.addAttribute("examList", examList);
		System.out.println(examList);
		return "student/eclass/examList";
	}
	
	@RequestMapping("/student/examTake")
	public String studentExamTake(ExamVO vo, Model model) {
		List<CourseExamVO> studentExam = service.getStudentExam(vo);
		model.addAttribute("examInfo", vo);
		model.addAttribute("studentExam", studentExam);
		return "student/eclass/examTake";
	}
	
	@PostMapping("/student/examTake")
	public String studentExamTaken(@RequestBody List<StudentExamVO> answerList) {
		int score = 0;
		for (StudentExamVO s : answerList) {
			score += s.getStudentRightOption();
		}
		ExamScoreVO studentScore = new ExamScoreVO();
		studentScore.setExamScore(score);
		studentScore.setExamCode(answerList.get(0).getExamCode());
		studentScore.setStudentId(answerList.get(0).getStudentId());
		studentScore.setExamTake("SUB");
		service.insertExamResult(answerList);
		service.updateExamScore(studentScore);
		return "redirect:/student/exam";
	}
	
	@RequestMapping("/student/examResult")
	public String examResult(ExamVO vo) {
		return "student/eclass/examResult";
	}

}
