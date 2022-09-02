package com.project.portal.bachelor.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String getSchedule(HttpServletRequest request, Model model) {
		String requestURI = request.getRequestURI();
		System.out.println(requestURI);
		int command = 0;
		if (requestURI.equals("/professor/schedule")) {
			command = 2;
		} else if (requestURI.equals("/student/schedule")) {
			command = 1;
		} else if (requestURI.equals("/admin/schedule")) {
			command = 3;
		}
		model.addAttribute("command", command);
		return "common/schedule";
	}
}
