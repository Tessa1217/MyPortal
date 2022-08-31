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

import com.project.portal.common.Criteria;
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

	// 교수 자신의 강의리스트 조회
	@RequestMapping("/professor/allCourseList")
	public String CourseList(CourseVO vo, Model model, Criteria cri) {
		model.addAttribute("allCourseList", service.allCourseList(cri));
		return "professor/course/courseList";
	}

	// 교수 강의별 수강평 리스트
	@RequestMapping("/professor/surveyList")
	
	public String ServeyList(CourseVO vo, 
							SurveyVO voo, 
							Model model, 
							HttpSession session) {
		vo.setProfessorId((int) session.getAttribute("id"));
		model.addAttribute("surveyList", service.surveyList(vo));
//		voo.setCourseCode(vo.getCourseCode());
//		model.addAttribute("surveySelect", service.surveySelect(voo));
		return "professor/course/surveyList";
	}

	// 교수 강의 수강평 상세보기
	@ResponseBody
	@RequestMapping("/professor/surveySelect")
	public HashMap<String, Object> ServeySelect(SurveyVO vo, 
											CourseVO voo, 
											SurveyAvgVO svo, 
											Model model) {
		vo = service.surveySelect(vo);
		svo = service.getSurveyStats(voo, svo);
		
		System.out.println(vo);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("survey", vo);
		map.put("chart", svo);
		return map;
	}
	
	
	
}
