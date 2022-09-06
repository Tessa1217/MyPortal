package com.project.portal.survey.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.mycourse.service.MyCourseVO;
import com.project.portal.survey.service.SurveyAnswerVO;
import com.project.portal.survey.service.SurveyService;
import com.project.portal.survey.service.SurveyVO;
import com.project.portal.tempcourse.web.TempcourseController;


// 설문지 조회 및 등록
// 작성자 : 박근형

@Controller
public class SurveyController {
	private static final Logger logger = LoggerFactory.getLogger(TempcourseController.class);
	
	@Autowired SurveyService service;
	
	// 설문지 조회
	@RequestMapping("/student/eclass/courseSurvey")
	public String selectCourseSurvey(SurveyVO vo, Model model,HttpSession session, MyCourseVO coursevo) {
		coursevo.setCourseCode((String)session.getAttribute("courseCode"));
		coursevo.setStudentId((int)session.getAttribute("id"));
		model.addAttribute("surveyState", service.selectSurveyState(coursevo));
		model.addAttribute("surveyContent" , service.selectSurvey(vo));
		
		return "/student/eclass/survey/courseSurvey";
			
	}
	
	// 학생 설문지 답안 제출
	@RequestMapping("/student/eclass/courseSurveyAnswer")
	public String insertSurvetAnswer(SurveyAnswerVO vo, MyCourseVO coursevo, HttpSession session) {
		
		String courseCode = (String)session.getAttribute("courseCode");
		vo.setCourseCode(courseCode);
		vo.setStudentId(22000001);
		coursevo.setStudentId(22000001);
		coursevo.setCourseCode(courseCode);
		System.out.println(vo);
		service.insertSurveyAnswer(vo);
		service.updateSurveyState(coursevo);
		return "redirect:/student/eclass/" + vo.getCourseCode();
	}
	
	
}
