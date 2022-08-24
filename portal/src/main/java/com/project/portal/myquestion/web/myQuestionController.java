package com.project.portal.myquestion.web;

import java.util.List;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.mycourse.web.myCourseController;
import com.project.portal.myquestion.service.myQuestionService;
import com.project.portal.myquestion.service.myQuestionVO;

@Controller
public class myQuestionController {
	private static final Logger logger = LoggerFactory.getLogger(myCourseController.class);
	
	@Autowired
	myQuestionService service;
	
	// 학생 질문 목록
	@RequestMapping("/student/eclass/courseQuestion")
	public String getStuMyQuestion(myQuestionVO vo, Model model) {
	
		vo.setStudentId("22000001");
		List<myQuestionVO> myQuestionList = service.getStuMyQuestion(vo.getStudentId());
		System.out.println(myQuestionList);
		//질문 목록 조회
		model.addAttribute("courseQuestion" , service.getStuMyQuestion(vo.getStudentId()));
		return "student/eclass/question/courseQuestion";
	}
	
	
	// 교수 질문 목록 조회
	@RequestMapping("/professor/eclass/courseQuestion")
	public String getProfMyQuestion(myQuestionVO vo, Model model) {
		
		return "professor/eclass/question/courseQuestion";
	}
}
