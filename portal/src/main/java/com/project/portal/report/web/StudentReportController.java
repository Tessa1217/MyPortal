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
import com.project.portal.report.service.ReportSubmissionVO;
import com.project.portal.report.service.ReportVO;
import com.project.portal.report.service.impl.StudentReportServiceImpl;

@Controller
public class StudentReportController {

	@Autowired
	CourseServiceImpl courseService;
	@Autowired
	StudentReportServiceImpl service;

	// 파일 다운로드 경로 가져오기
	@Value("${spring.servlet.multipart.location}")
	private String filelocation;

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
	public String reportSubmit(@PathVariable String reportCode, Model model, ReportVO vo ,ReportFileVO filevo) {
		vo.setReportCode(reportCode);
		model.addAttribute("reportDetail", service.getReportDetail(vo.getReportCode()));
		model.addAttribute("reportFile" , service.getFile(vo.getReportCode()));
		return "student/eclass/report/submitReport";
	}

	// 과제 제출 처리
	@PostMapping("/student/eclass/reportSubmit")
	public String reportSubmission(@RequestParam(name = "rFile") MultipartFile file, ReportSubmissionVO resub, 
			ReportFileVO filevo, HttpSession session) throws IllegalStateException, IOException {
		int studentId = (int) session.getAttribute("id");
		
		String courseCode = (String) session.getAttribute("courseCode");
		// 업로드 파일이 있을 때
		if (!file.isEmpty()) {
			// 원본 파일명 추출
			String fileOriName = file.getOriginalFilename();
			// 확장자 추출
			String fileNameExtension = fileOriName.substring(fileOriName.lastIndexOf("."));
			// 랜덤 파일명 값 생성
			String fileName = UUID.randomUUID().toString().replace("-", "") + fileNameExtension;
			// 파일 URL
			String fileUrl = filelocation + "/report/" + courseCode + "/" + fileName;
			// 파일객체생성
			File uploadfile = new File(fileUrl);

			// 파일을 경로에 저장
			file.transferTo(uploadfile);
			
			// 파일 테이블에 insert 하기 위해 필요한 정보 set
			filevo.setReportFileExtension(fileNameExtension); // 확장자
			filevo.setReportFileName(fileOriName); // 원본파일이름
			filevo.setReportFilePath(fileUrl); // 파일 경로
			filevo.setReportFileStoredName(fileName); // uuid 랜덤 파일명
			filevo.setSubmitId(studentId); // 학번
			filevo.setUserCode("02"); // 학생 유저코드 02
			
			// 파일 테이블 insert
			service.uploadFile(filevo);
			
		}
		
		// 제출 테이블 insert
		resub.setReportFileCode(filevo.getReportFileCode());
		resub.setStudentId(studentId); // 학번
		service.insertReportSubmission(resub);

		return "redirect:/student/eclass/reportList";
	}

}
