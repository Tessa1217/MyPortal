package com.project.portal.bachelor.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.portal.bachelor.service.BachelorScheduleService;
import com.project.portal.bachelor.service.BachelorScheduleVO;
import com.project.portal.tempcourse.web.TempcourseController;

// 작성자: 김진형
@Controller
public class BachelorController {
	
	private static final Logger logger = LoggerFactory.getLogger(TempcourseController.class);
	
	@Autowired BachelorScheduleService service;
	
//	@RequestMapping("/common/scheduleList")
//	@ResponseBody
//	public List<BachelorScheduleVO> getSchedule(BachelorScheduleVO vo) {
//		return service.scheduleList(vo);
//	}
	
	// 학사일정 조회
	@RequestMapping({"/student/schedule", "/professor/schedule", "/admin/schedule"})
	public String getSchedule(HttpServletRequest request, Model model, BachelorScheduleVO vo, HttpSession session) {
		String requestURI = request.getRequestURI();
		//vo.setYear((int)session.getAttribute("year"));
		
		if(vo.getYear() == 0 ) {
			vo.setYear((int)session.getAttribute("year"));
			vo.setSemester((int)session.getAttribute("semester"));
		}
		// System.out.println(requestURI);
		int command =0;
		
		if (requestURI.equals("/professor/schedule")) {
			command = 2;
		} else if (requestURI.equals("/student/schedule")) {
			command = 1;
		} else if (requestURI.equals("/admin/schedule")) {
			command = 3;
		}
		
		
		model.addAttribute("command", command);
		model.addAttribute("year", vo.getYear());
		//model.addAttribute(vo)
		model.addAttribute("scheduleList", service.scheduleList(vo));
		return "common/schedule";
	}
}
