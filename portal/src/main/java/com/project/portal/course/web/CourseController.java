package com.project.portal.course.web;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.common.Criteria;
import com.project.portal.course.service.CourseService;
import com.project.portal.course.service.CourseVO;
import com.project.portal.mycourse.web.myCourseController;

// 작성자: 김진형
@Controller
public class CourseController {
	private static final Logger logger = LoggerFactory.getLogger(myCourseController.class);
	
	@Autowired CourseService service;
	
	//교수 자신의 강의리스트 조회
	@RequestMapping("/professor/allCourseList")
	public String CourseList(CourseVO vo, Model model, Criteria cri) {
		model.addAttribute("allCourseList",service.allCourseList(cri));
		return "professor/course/courseList";
	}

}
