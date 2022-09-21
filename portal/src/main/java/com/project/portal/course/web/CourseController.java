package com.project.portal.course.web;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.portal.bachelor.service.BachelorScheduleService;
import com.project.portal.bachelor.service.BachelorScheduleVO;
import com.project.portal.common.Criteria;
import com.project.portal.common.PageDTO;
import com.project.portal.course.service.CourseService;
import com.project.portal.course.service.CourseVO;
import com.project.portal.mycourse.web.myCourseController;
import com.project.portal.survey.service.SurveyAvgVO;
import com.project.portal.survey.service.SurveyVO;

// 작성자: 김진형
@Controller
public class CourseController {
	private static final Logger logger = LoggerFactory.getLogger(myCourseController.class);

	@Autowired
	CourseService service;
	
	@Autowired BachelorScheduleService schedule;

	// 교수 자신의 강의리스트 조회
	@RequestMapping("/professor/allCourseList")
	public String CourseList(CourseVO vo, Model model, BachelorScheduleVO voo, HttpSession session) {
		vo.setProfessorId((int) session.getAttribute("id"));
		voo.setYear((int)session.getAttribute("year"));
		voo.setSemester((int)session.getAttribute("semester"));
		model.addAttribute("year", voo.getYear());
		model.addAttribute("semester", voo.getSemester());
		model.addAttribute("pageMaker", new PageDTO(service.getTotal2(vo), vo.getAmount(), vo));
		model.addAttribute("allCourseList", service.allCourseList(vo));
		return "professor/course/courseList";
	}

	// 교수 강의별 수강평 리스트
	@RequestMapping("/professor/surveyList")
	
	public String ServeyList(CourseVO vo, 
							Model model,
							BachelorScheduleVO voo,
							HttpSession session) {
		voo.setYear((int)session.getAttribute("year"));
		voo.setSemester((int)session.getAttribute("semester"));
		
		vo.setProfessorId((int) session.getAttribute("id"));
		model.addAttribute("surveyList", service.surveyList(vo));
		model.addAttribute("pageMaker", new PageDTO(service.getTotal(vo), vo.getAmount(), vo));
		model.addAttribute("year", voo.getYear());
		model.addAttribute("semester", voo.getSemester());
		return "professor/course/surveyList";
	}

	// 교수 강의 수강평 상세보기
	@ResponseBody
	@RequestMapping("/professor/surveySelect")
	public HashMap<String, Object> ServeySelect(SurveyVO vo, 
											CourseVO voo, 
											SurveyAvgVO svo, 
											Model model,
											HttpSession session) {
		
		vo = service.surveySelect(vo);
		voo.setProfessorId((int) session.getAttribute("id"));
		svo = service.getSurveyStats(voo, svo);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("survey", vo);
		map.put("chart", svo);
		return map;
	}
	
	
	
}
