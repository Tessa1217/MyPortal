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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.portal.common.Criteria;
import com.project.portal.common.PageDTO;
import com.project.portal.course.service.CourseVO;
import com.project.portal.course.service.impl.CourseServiceImpl;
import com.project.portal.report.service.ReportFileVO;
import com.project.portal.report.service.ReportObjectionVO;
import com.project.portal.report.service.ReportSubmissionVO;
import com.project.portal.report.service.ReportVO;
import com.project.portal.report.service.impl.ProfessorReportServiceImpl;

// 작성자: 권유진 

@Controller
@RequestMapping("/professor/eclass")
public class ProfessorReportController {
	
	@Autowired 
	ProfessorReportServiceImpl service;
	@Autowired
	CourseServiceImpl courseService;
	
	// 파일 다운로드 경로
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	// 과제 리스트 
	@RequestMapping("/reportList")
	public String getReportList(Model model, 
								Criteria cri, 
								HttpSession session) {
		List<ReportVO> reportList = service.getReportList((CourseVO) session.getAttribute("courseInfo"), null, cri);
		model.addAttribute("reportList", reportList);
		model.addAttribute("pageMaker", new PageDTO(service.getTotal((CourseVO) session.getAttribute("courseInfo"), cri), 10, cri));
		return "professor/eclass/report/reportList";
	}
	
	// 과제 등록 페이지 이동 
	@RequestMapping("/insertReport")
	public String insertReportForm(Model model) {
		return "professor/eclass/report/insertReport";
	}
	
	// 과제 등록 
	@PostMapping("/insertReport")
	public String insertReport(@RequestParam("file") MultipartFile file, 
								ReportVO vo, 
								Model model, 
								HttpSession session) throws IllegalStateException, IOException {
		CourseVO course = (CourseVO) session.getAttribute("courseInfo");
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
	@PostMapping("/reportFileList")
	public String getWholeFile(ReportFileVO vo, 
							Criteria cri, 
							Model model,
							HttpSession session) {
		vo.setSubmitId((int) session.getAttribute("id"));
		vo.setUserCode("01");
		model.addAttribute("fileList", service.getProfWholeFile(vo, cri));
		model.addAttribute("pageMaker", new PageDTO(service.getFileTotal(vo, cri), cri.getAmount(), cri));
		return "layout/fragments/professor-eclass/report/fileList :: #tableFragment";
	}
	
	// 상세 보기 페이지 (ON/OFF 이용해서 상세 보기 + 수정 같이 한 페이지에서 처리)
	@RequestMapping("/detailReport/{reportCode}")
	public String detailReport(@PathVariable String reportCode, 
								ReportVO vo, 
								Model model) {
		vo.setReportCode(reportCode);
		model.addAttribute("command", 1);
		model.addAttribute("report", service.getReportList(null, vo, null).get(0));
		return "professor/eclass/report/detailReport";
	}
	
	// 새로운 파일이 업로드 되어 MultipartFile이 존재할 경우 기존에 있는 파일은 지워줌
	@PostMapping("/updateReport")
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
			CourseVO course = (CourseVO) session.getAttribute("courseInfo");
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
	
	@DeleteMapping("/deleteReport") 
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
	@RequestMapping("/studentScore")
	public String studentScoreForm(Model model,
									CourseVO course,
									Criteria cri,
									HttpSession session) {
		course = (CourseVO) session.getAttribute("courseInfo");
		List<ReportSubmissionVO> reportList = service.getStudentReportList(course, cri);
		List<ReportVO> rList = service.getReportList(course, null, null);
		model.addAttribute("reportList", reportList);
		model.addAttribute("rList", rList);
		ReportVO report = new ReportVO();
		report.setReportCode(cri.getKeyword());
		model.addAttribute("pageMaker", new PageDTO(service.getReportTotal(service.getReportList(course, report, null), cri), cri.getAmount(), cri));
		return "professor/eclass/report/studentReportScore";
	}
	
	@PostMapping("/updateScore")
	@ResponseBody
	public String updateScore(@RequestBody List<ReportSubmissionVO> subList) {
		service.updateScore(subList);
		return "success";
	}
	
	//과제 이의신청 관리 리스트
	@RequestMapping("/reportObjection")
	public String getStudentObjectionList (ReportObjectionVO vo , Model model, HttpSession session , Criteria cri) {
		
		vo.setCourseCode((String)session.getAttribute("courseCode"));
		model.addAttribute("reportObjection", service.getStudentObjectionList(vo , cri));
		model.addAttribute("pageMaker", new PageDTO(service.getReportObjectionTotal(vo, cri), cri.getAmount(), cri));
		
		return "professor/eclass/report/reportObjection";
	}
	
	//과제 이의신청 상세조회
	@RequestMapping("/report/reportObjectionDetail/{reportSubmissionCode}")
	public String getStudentObjectionDetail (@PathVariable String reportSubmissionCode , ReportObjectionVO vo , Model model, HttpSession session) {
		vo.setCourseCode((String)session.getAttribute("courseCode"));
		vo.setReportSubmissionCode(reportSubmissionCode);
		model.addAttribute("reportObjectionDetail", service.getStudentObjectionDetail(vo));
		return "professor/eclass/report/reportObjectionDetail"; 
	}
	
	// 과제 이의신청 업데이트
	@PostMapping("/updateObjection")
	public String updateObjection (ReportObjectionVO vo , Model model, HttpSession session) {
		
		
		service.updateObjection(vo);
		service.updateObjectionScore(vo);
		return "redirect:/professor/eclass/reportObjection";
	}
}
