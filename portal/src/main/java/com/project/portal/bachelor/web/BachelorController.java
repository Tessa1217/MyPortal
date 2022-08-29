package com.project.portal.bachelor.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.bachelor.service.BachelorScheduleService;
import com.project.portal.tempcourse.web.TempcourseController;

// 작성자: 김진형
@Controller
public class BachelorController {
	
	private static final Logger logger = LoggerFactory.getLogger(TempcourseController.class);
	
	@Autowired BachelorScheduleService service;
	
	// 학사일정 조회
	@RequestMapping({"/student/schedule", "/professor/schedule", "/admin/schedule"})
	public String getSchedule() {
		return "common/schedule";
	}
}
