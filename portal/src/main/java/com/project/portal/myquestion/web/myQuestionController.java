package com.project.portal.myquestion.web;

import javax.servlet.http.HttpSession;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.portal.bachelor.service.impl.BachelorNoticeServiceImpl;
import com.project.portal.common.Criteria;
import com.project.portal.common.PageDTO;
import com.project.portal.course.service.CourseVO;
import com.project.portal.course.service.impl.CourseServiceImpl;
import com.project.portal.mycourse.web.myCourseController;
import com.project.portal.myquestion.service.myQuestionAnswerVO;
import com.project.portal.myquestion.service.myQuestionService;
import com.project.portal.myquestion.service.myQuestionVO;

@Controller
public class myQuestionController {
	private static final Logger logger = LoggerFactory.getLogger(myCourseController.class);
	
	@Autowired
	myQuestionService service;
	@Autowired CourseServiceImpl courseService;
	@Autowired
	BachelorNoticeServiceImpl Bservice;
	
	@ModelAttribute("courseInfo")
	public CourseVO course(HttpSession session) {
		CourseVO course = new CourseVO();
		course.setCourseCode((String) session.getAttribute("courseCode"));
		return courseService.getWeeklyInfo(course);
	}
	
	// 학생 질문 목록
	@RequestMapping("/student/eclass/courseQuestion")
	public String getStuMyQuestion(myQuestionVO vo, Model model, HttpSession session, Criteria cri) {
		vo.setStudentId((int)session.getAttribute("id"));
		vo.setCourseCode((String)session.getAttribute("courseCode"));
		//질문 목록 조회
		model.addAttribute("courseQuestion" , service.getStuMyQuestion(vo, cri));
		//페이지
		model.addAttribute("pageMaker", new PageDTO(service.getStuTotal(vo), cri.getAmount(), cri));
		
		return "student/eclass/question/courseQuestion";
	}
	
	
	// 교수 질문 목록 조회
	@RequestMapping("/professor/eclass/courseQuestion")
	public String getProfMyQuestion(myQuestionVO vo, Model model, HttpSession session, Criteria cri) {
		vo.setCourseCode((String)session.getAttribute("courseCode"));
		model.addAttribute("courseProfQuestion" , service.getProfMyQuestion(vo, cri));
		model.addAttribute("pageMaker", new PageDTO(service.getProfTotal(vo), cri.getAmount(), cri));
		return "professor/eclass/lecture/courseQuestion";
	}
	
	// 학생 질문 상세조회
	@PostMapping("/student/eclass/courseQuestionDetail")
	@ResponseBody
	public myQuestionVO getStuMyQuestionDetail(myQuestionVO vo, Model model) {
		vo = service.getStuMyQuestionDetail(vo.getLectureQuestionNum());
		return vo;
	}
	
	// 교수 질문 상세조회
	@PostMapping("/professor/eclass/courseQuestionDetail")
	@ResponseBody
	public myQuestionVO getProfMyQuestionDetail(myQuestionVO vo, Model model) {
		vo = service.getProfMyQuestionDetail(vo.getLectureQuestionNum());
		return vo;
	}
	
	// 교수 답변 등록
	@PostMapping("/professor/eclass/courseQuestionInsert")
	@ResponseBody
	public String insertProfMyQuestion(myQuestionAnswerVO vo) {
		service.insertProfMyQuestion(vo);
		return "success";
	}
	
	// 교수 답변 수정
	@PostMapping("/professor/eclass/courseQuestionUpdate")
	@ResponseBody
	public String modifyProfMyQuestion(myQuestionAnswerVO vo) {
		
		service.modifyProfMyQuestion(vo);
		return "success";
	}
}