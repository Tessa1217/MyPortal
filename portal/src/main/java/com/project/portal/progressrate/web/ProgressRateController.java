package com.project.portal.progressrate.web;

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
import com.project.portal.progressrate.service.ProgressRateService;
import com.project.portal.progressrate.service.ProgressRateVO;

@Controller
public class ProgressRateController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(ProgressRateController.class);
	
	@Autowired ProgressRateService Pservice;
	
	
	@RequestMapping("/student/eclass/progressRate")
	public String stuProgressRate (ProgressRateVO vo ,Model model , HttpSession session) {
		// 강의 진행도 표시
		vo.setStudentId((int)session.getAttribute("id"));
		vo.setCourseCode((String)session.getAttribute("courseCode"));
		model.addAttribute("courseRate" , Pservice.selectLectureProgress(vo));
		
		// 과제 진행도 표시
		model.addAttribute("reportRate", Pservice.selectReportProgress(vo));	
		// 출석률 표시
		model.addAttribute("attRate", Pservice.selectReportProgressRate(vo));
		
		// 시험정보 표시
		model.addAttribute("examMidRate", Pservice.selectMidExamProgress(vo));
		model.addAttribute("examFinalRate", Pservice.selectFinalExamProgress(vo));
		return "student/eclass/progressrate/progressrate";
		
		
	}
	
	
}
