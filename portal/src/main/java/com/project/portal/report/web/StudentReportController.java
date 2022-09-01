package com.project.portal.report.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.project.portal.course.service.CourseVO;
import com.project.portal.course.service.impl.CourseServiceImpl;
import com.project.portal.report.service.ReportSubmissionVO;
import com.project.portal.report.service.ReportVO;
import com.project.portal.report.service.impl.StudentReportServiceImpl;

@Controller
public class StudentReportController {
	
	@Autowired
	CourseServiceImpl courseService;
	@Autowired
	StudentReportServiceImpl service;
	
	// 주차 정보
	@ModelAttribute("courseInfo")
	public CourseVO course(HttpSession session) {
		CourseVO course = new CourseVO();
		course.setCourseCode((String) session.getAttribute("courseCode"));
		return courseService.getWeeklyInfo(course);
	}
	
	// 과제 리스트
	@RequestMapping("/student/eclass/reportList")
	public String getReportList(Model model, CourseVO vo) {
		vo = (CourseVO) model.getAttribute("courseInfo");
		List<ReportVO> reportList = service.getReportList(vo, null);
		model.addAttribute("reportList", reportList);
		return "student/eclass/report/reportList";
	}
	
	
	
	// 과제 제출 페이지 이동
	@RequestMapping("/student/eclass/reportSubmit/{reportCode}")
	public String reportSubmit(@PathVariable String reportCode, Model model, ReportVO vo) {
		vo.setReportCode(reportCode);
		model.addAttribute("reportDetail", service.getReportDetail(vo.getReportCode()));
		return "student/eclass/report/reportSubmit";
	}
	
	// 과제 제출 처리
	@PostMapping("/student/eclass/reportSubmit")
	public String reportSubmission(ReportSubmissionVO resub, MultipartFile file) {
		
		
		
		return "redirect:/student/eclass/reportList";
	}

}
