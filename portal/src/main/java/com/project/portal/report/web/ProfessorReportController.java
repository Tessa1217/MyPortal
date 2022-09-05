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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.portal.course.service.CourseVO;
import com.project.portal.course.service.impl.CourseServiceImpl;
import com.project.portal.report.service.ReportFileVO;
import com.project.portal.report.service.ReportSubmissionVO;
import com.project.portal.report.service.ReportVO;
import com.project.portal.report.service.impl.ProfessorReportServiceImpl;

// 작성자: 권유진 

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
		List<ReportVO> reportList = service.getReportList((CourseVO) model.getAttribute("courseInfo"), null);
		model.addAttribute("reportList", reportList);
		return "professor/eclass/report/reportList";
	}
	
	// 과제 등록 페이지 이동 
	@RequestMapping("/professor/eclass/insertReport")
	public String insertReportForm(Model model) {
		return "professor/eclass/report/insertReport";
	}
	
	// 과제 등록 
	@PostMapping("/professor/eclass/insertReport")
	public String insertReport(@RequestParam("file") MultipartFile file, ReportVO vo, Model model, HttpSession session) throws IllegalStateException, IOException {
		CourseVO course = (CourseVO) model.getAttribute("courseInfo");
		course.setProfessorId((int) session.getAttribute("id"));
		// 과제 등록하기 전 과제 파일 테이블 저장 위해 관련 데이터 setter 통해 담기 
		ReportFileVO report = newFile(file, course);
		vo.setReportFile(report);
		
		// 과제 정보, 과제 파일 테이블에 등록한 값들 담기 
		service.insertReport(vo);
		
		// 파일 업로드 진행 필요
		File fileUpload = new File(report.getReportFilePath());
		fileUpload.getParentFile().mkdirs();
		file.transferTo(fileUpload);
		
		return "redirect:/professor/eclass/reportList";
	}
	
	// 교수의 파일 전체 가져오기
	@PostMapping("/professor/eclass/getWholeFile")
	public String getWholeFile(ReportFileVO vo, Model model) {
		model.addAttribute("fileList", service.getProfWholeFile(vo));
		return "layout/fragments/professor-eclass/report/fileList :: #oldFileList";
	}
	
	// 상세 보기 페이지 (ON/OFF 이용해서 상세 보기 + 수정 같이 한 페이지에서 처리)
	@RequestMapping("/professor/eclass/detailReport/{reportCode}")
	public String detailReport(@PathVariable String reportCode, ReportVO vo, Model model) {
		vo.setReportCode(reportCode);
		model.addAttribute("command", 1);
		model.addAttribute("report", service.getReportList(null, vo).get(0));
		return "professor/eclass/report/detailReport";
	}
	
	// 새로운 파일이 업로드 되어 MultipartFile이 존재할 경우 기존에 있는 파일은 지워줌
	@PostMapping("/professor/eclass/updateReport")
	public String updateReport(ReportVO vo, 
								@RequestParam("file") MultipartFile file, 
								HttpSession session, 
								Model model) {
		// 수정하려는 파일이 있을 때
		if (!file.isEmpty()) {
			ReportFileVO storedReportFile = service.getFile(vo.getReportFileCode(), "01");
			File storedFile = new File(storedReportFile.getReportFilePath());
			if (storedFile.exists()) {
				storedFile.delete();
			}
			CourseVO course = (CourseVO) model.getAttribute("courseInfo");
			course.setProfessorId((int) session.getAttribute("id"));
			ReportFileVO newFile = newFile(file, course);
			vo.setReportFile(newFile);
			service.updateReport(vo);
			File fileUpload = new File(newFile.getReportFilePath());
			fileUpload.getParentFile().mkdirs();
			try {
				file.transferTo(fileUpload);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		// 수정하려는 파일은 없고 정보만 수정할 때
		}
		if (file.isEmpty()) {
			service.updateReportOnly(vo);
		}
		
		return "redirect:/professor/eclass/reportList";
	}
	
	@DeleteMapping("/professor/eclass/deleteReport") 
	@ResponseBody
	private String deleteFile(@RequestBody ReportVO report) {
		service.deleteReport(report);
		return "success";
	}
	
	// 파일 세팅하는 메서드 
	private ReportFileVO newFile(MultipartFile file, CourseVO course) {
		ReportFileVO reportFile = new ReportFileVO();
		reportFile.setReportFileName(file.getOriginalFilename());
		reportFile.setReportFileStoredName(UUID.randomUUID().toString().replaceAll("-", "") + file.getOriginalFilename());
		reportFile.setReportFilePath(uploadPath + "/report/" + course.getCourseCode() + "/" + reportFile.getReportFileStoredName());
		reportFile.setReportFileExtension(file.getContentType());
		// 공통 코드 중 유저 코드 사용 (교수 = 01)
		reportFile.setUserCode("01");
		// Submit Id는 입력한 사람의 ID값 등록 (교수와 학생의 경우 유저 코드와 아이디 길이 상이)
		reportFile.setSubmitId(course.getProfessorId());
		return reportFile;
	}
	
	// 수강생 과제 관리
	@RequestMapping("/professor/eclass/studentScore")
	public String studentScoreForm(Model model) {
		return "professor/eclass/report/studentReportScore";
	}
	
	// 수강생 과제 리스트 조회 
	@PostMapping("/professor/eclass/studentScore")
	public String weeklyStudentScore(Model model, String reportCode, CourseVO course) {
		course = (CourseVO) model.getAttribute("courseInfo");
		ReportVO vo = null;
		if (reportCode != null) {
			vo = new ReportVO();
			vo.setReportCode(reportCode);
		}
		List<ReportSubmissionVO> reportList = service.getStudentReportList(course, vo);
		List<ReportVO> rList = service.getReportList(course, null);
		model.addAttribute("reportList", reportList);
		model.addAttribute("rList", rList);
		model.addAttribute("reportCode", reportCode);
		return "layout/fragments/professor-eclass/report/studentScoreFragment :: #studentScore";
	}
	
	//@RequestMapping("/professor/eclass/studentScore/")
	
}
