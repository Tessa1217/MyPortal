package com.project.portal.report.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.portal.course.service.CourseVO;
import com.project.portal.course.service.impl.CourseServiceImpl;
import com.project.portal.report.service.ReportFileVO;
import com.project.portal.report.service.ReportVO;
import com.project.portal.report.service.impl.ProfessorReportServiceImpl;

@Controller
public class ProfessorReportController {
	
	@Autowired 
	ProfessorReportServiceImpl service;
	@Autowired
	CourseServiceImpl courseService;
	
	// 세션에 코스와 코스 주차 정보 담아서 사용 
	@ModelAttribute("courseInfo")
	public CourseVO course(HttpSession session) {
		CourseVO course = new CourseVO();
		course.setCourseCode((String) session.getAttribute("courseCode"));
		return courseService.getWeeklyInfo(course);
	}
	
	// 파일 다운로드 경로
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	// 과제 리스트 
	@RequestMapping("/professor/eclass/reportList")
	public String getReportList(Model model) {
		List<ReportVO> reportList = service.getReportList((CourseVO) model.getAttribute("courseInfo"));
		model.addAttribute("reportList", reportList);
		return "professor/eclass/report/reportList";
	}
	
	// 과제 등록 페이지 이동 
	@RequestMapping("/professor/eclass/insertReport")
	public String insertReportForm() {
		return "professor/eclass/report/insertReport";
	}
	
	// 과제 등록 
	@PostMapping("/professor/eclass/insertReport")
	public String insertReport(@RequestParam("file") MultipartFile file, ReportVO vo, Model model) throws IllegalStateException, IOException {
		
		// 과제 등록하기 전 과제 파일 테이블 저장 위해 관련 데이터 setter 통해 담기 
		CourseVO course = (CourseVO) model.getAttribute("courseInfo");
		String courseCode = course.getCourseCode();
		ReportFileVO report = new ReportFileVO();
		report.setReportFileName(file.getOriginalFilename());
		report.setReportFileStoredName(UUID.randomUUID().toString().replaceAll("-", "") + file.getOriginalFilename());
		report.setReportFilePath(uploadPath + "/report/" + courseCode + "/" + report.getReportFileStoredName());
		report.setReportFileExtension(file.getContentType());
		// 공통 코드 중 유저 코드 사용 (교수 = 01)
		report.setUserCode("01");
		// Submit Id는 입력한 사람의 ID값 등록 (교수와 학생의 경우 유저 코드와 아이디 길이 상이)
		report.setSubmitId(course.getProfessorId());
		vo.setReportFile(report);
		
		// 과제 정보, 과제 파일 테이블에 등록한 값들 담기 
		service.insertReport(vo);
		
		// 파일 업로드 진행 필요
		File fileUpload = new File(report.getReportFilePath());
		fileUpload.getParentFile().mkdirs();
		file.transferTo(fileUpload);
		
		return "redirect:/professor/eclass/reportList";
	}
	
	@RequestMapping("/professor/eclass/updateReport/{reportCode}")
	public String updateReport(@PathVariable String reportCode, ReportVO vo, Model model) {
		return "professor/eclass/report/updateReport";
	}
}
