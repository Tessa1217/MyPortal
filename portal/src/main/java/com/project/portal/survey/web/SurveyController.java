package com.project.portal.survey.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.bachelor.service.BachelorScheduleService;
import com.project.portal.bachelor.service.BachelorScheduleVO;
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
	@Autowired BachelorScheduleService schedule;
	
	
	// 현재 학기의 설문평가 기간정보 가져오기
	@ModelAttribute("yearSemester") 
	   public BachelorScheduleVO getYearSemester(HttpSession session) {
	      BachelorScheduleVO s = new BachelorScheduleVO();
	      s.setDetailCode("PROF00");
	      s.setYear((int)session.getAttribute("year"));
	      s.setSemester((int)session.getAttribute("semester"));
	      return schedule.getCurrentScheduleInfo(s);
	   }
	
	// 설문지 조회
	@RequestMapping("/student/eclass/courseSurvey")
	public String selectCourseSurvey(SurveyVO vo, Model model,HttpSession session, MyCourseVO coursevo) {
		coursevo.setCourseCode((String)session.getAttribute("courseCode"));
		coursevo.setStudentId((int)session.getAttribute("id"));
		model.addAttribute("surveyState", service.selectSurveyState(coursevo));
		model.addAttribute("surveyContent" , service.selectSurvey(vo));
		
		return "student/eclass/survey/courseSurvey";
			
	}
	
	// 학생 설문지 답안 제출
	@RequestMapping("/student/eclass/courseSurveyAnswer")
	public String insertSurvetAnswer(SurveyAnswerVO vo, MyCourseVO coursevo, HttpSession session) {
		
		String courseCode = (String)session.getAttribute("courseCode");
		vo.setCourseCode(courseCode);
		vo.setStudentId((int)session.getAttribute("id"));
		coursevo.setStudentId((int)session.getAttribute("id"));
		coursevo.setCourseCode(courseCode);
		service.insertSurveyAnswer(vo);
		service.updateSurveyState(coursevo);
		return "redirect:/student/eclass/" + vo.getCourseCode();
	}
	
	
}
