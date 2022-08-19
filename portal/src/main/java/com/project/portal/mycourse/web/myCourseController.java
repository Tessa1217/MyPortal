package com.project.portal.mycourse.web;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.mycourse.service.MyCourseVO;
import com.project.portal.mycourse.service.impl.MyCourseServiceImpl;

@Controller
public class myCourseController {
	private static final Logger logger = LoggerFactory.getLogger(myCourseController.class);
	
	@Autowired
	MyCourseServiceImpl service;
	
	// 학생 수강 목록 조회
	
	@RequestMapping("student/courseList")
	public String getstuMyCourse(MyCourseVO vo, Model model) {
		
		
		return "student/courseList";
	}
	
}


	
