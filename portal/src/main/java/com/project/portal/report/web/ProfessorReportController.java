package com.project.portal.report.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.portal.course.service.CourseVO;
import com.project.portal.course.service.impl.CourseServiceImpl;
import com.project.portal.report.service.ReportVO;
import com.project.portal.report.service.impl.ProfessorReportServiceImpl;

@Controller
public class ProfessorReportController {
	
	@Autowired 
	ProfessorReportServiceImpl service;
	@Autowired
	CourseServiceImpl courseService;
	
	@ModelAttribute("courseInfo")
	public CourseVO course(HttpSession session) {
		CourseVO course = new CourseVO();
		course.setCourseCode((String) session.getAttribute("courseCode"));
		return courseService.getWeeklyInfo(course);
	}
	
	@RequestMapping("/professor/eclass/reportList")
	public String getReportList(Model model) {
		List<ReportVO> reportList = service.getReportList((CourseVO) model.getAttribute("courseInfo"));
		model.addAttribute("reportList", reportList);
		return "professor/eclass/report/reportList";
	}
	
	@RequestMapping("/professor/eclass/insertReport")
	public String insertReportForm() {
		return "professor/eclass/report/insertReport";
	}
	
	@PostMapping("/professor/eclass/insertReport")
	@ResponseBody
	public String insertReport(MultipartFile file, ReportVO vo) {
		
		// 파일 업로드 진행 필요
		service.insertReport(vo);
		return "success";
	}
}
