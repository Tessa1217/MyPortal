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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.portal.bachelor.service.BachelorGroupVO;
import com.project.portal.bachelor.service.BachelorScheduleService;
import com.project.portal.bachelor.service.BachelorScheduleVO;
import com.project.portal.common.service.impl.CodeServiceImpl;
import com.project.portal.tempcourse.web.TempcourseController;

// 작성자: 김진형
@Controller
public class BachelorController {
	
	private static final Logger logger = LoggerFactory.getLogger(TempcourseController.class);
	
	@Autowired BachelorScheduleService service;
	@Autowired CodeServiceImpl codeService;
	
	@ModelAttribute("codeList")
	public List<BachelorGroupVO> getCodeList() {
		return service.getScheduleCode();
	}
	
	// 학사일정 조회
	@RequestMapping({"/student/schedule", "/professor/schedule", "/admin/schedule"})
	public String getSchedule(HttpServletRequest request, Model model, BachelorScheduleVO vo, HttpSession session) {
		String requestURI = request.getRequestURI();
		if(vo.getYear() == 0 ) {
			vo.setYear((int)session.getAttribute("year"));
			vo.setSemester((int)session.getAttribute("semester"));
		}
		int command = setCommand(requestURI);
		model.addAttribute("command", command);
		model.addAttribute("year", vo.getYear());
		model.addAttribute("scheduleList", service.scheduleList(vo));
		return "common/schedule";
	}
	
	@PostMapping("/getCalendar")
	@ResponseBody
	public List<BachelorScheduleVO> getFullCalendar(BachelorScheduleVO vo, 
													HttpSession session) {
		
		 if(vo.getYear() == 0) { 
			 vo.setYear((int)session.getAttribute("year")); 
			 }
		 
		// vo.setSemester((int)session.getAttribute("semester")); 
		return service.scheduleList(vo);
	}
	
	// 학사일정 일괄 등록
	@RequestMapping("/admin/scheduleAllInsert")
	@ResponseBody
	public String scheduleAllInsert(@RequestBody List<BachelorScheduleVO> vo, Model model) {
		service.scheduleAllInsert(vo);
		return "success";
	}
	
	// 학사일정 단건 등록
		@RequestMapping("/admin/scheduleInsert")
		@ResponseBody
		public String scheduleInsert(BachelorScheduleVO vo, Model model) {
			service.scheduleInsert(vo);
			return "success";
		}
	
	private int setCommand(String requestURI) {
		int command = 0;
		if (requestURI.equals("/professor/schedule")) {
			command = 2;
		} else if (requestURI.equals("/student/schedule")) {
			command = 1;
		} else if (requestURI.equals("/admin/schedule")) {
			command = 3;
		}
		return command;
	}
	
	
	@RequestMapping("/admin/getYearSemester")
	@ResponseBody
	public BachelorScheduleVO getYearSemester(BachelorScheduleVO vo, Model model) {
		;
		return service.getYearSemester(vo);
	}
}
