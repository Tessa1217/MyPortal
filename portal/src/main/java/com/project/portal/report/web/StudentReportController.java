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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.portal.bachelor.service.impl.BachelorNoticeServiceImpl;
import com.project.portal.common.Criteria;
import com.project.portal.common.PageDTO;
import com.project.portal.course.service.CourseVO;
import com.project.portal.course.service.impl.CourseServiceImpl;
import com.project.portal.report.service.ReportFileVO;
import com.project.portal.report.service.ReportObjectionVO;
import com.project.portal.report.service.ReportSubmissionVO;
import com.project.portal.report.service.ReportVO;
import com.project.portal.report.service.impl.StudentReportServiceImpl;

@Controller
public class StudentReportController {

	@Autowired
	CourseServiceImpl courseService;
	@Autowired
	StudentReportServiceImpl service;
	@Autowired
	BachelorNoticeServiceImpl Bservice;

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
	public String getReportList(Model model, CourseVO vo, ReportVO rvo, HttpSession session, Criteria cri) {
		vo.setStudentId((int) session.getAttribute("id"));
		vo.setCourseCode((String)session.getAttribute("courseCode"));
		rvo.setStudentId((int) session.getAttribute("id"));
		List<ReportVO> reportList = service.getReportList(vo, rvo, cri);
		

		// 과제 리스트
		model.addAttribute("reportList", reportList);
		// paging
		model.addAttribute("pageMaker", new PageDTO(service.getTotal(rvo), cri.getAmount(), cri));
		return "student/eclass/report/reportList";
	}

	// 과제 제출 페이지 이동
	@RequestMapping("/student/eclass/reportSubmit/{reportCode}")
	public String reportSubmit(@PathVariable String reportCode, Model model, ReportVO vo, ReportFileVO filevo,
			HttpSession session, ReportSubmissionVO rsubvo) {
		int studentId = (int) session.getAttribute("id");
		vo.setReportCode(reportCode);
		vo.setStudentId(studentId);
		// 과제 제출 내용 조회
		rsubvo = service.selectDetail(vo);
		model.addAttribute("profFile", service.getProfFileInfo(vo));
		model.addAttribute("reportDetail", service.getReportDetail(vo));
		return "student/eclass/report/submitReport";
	}

	// 과제 수정 페이지 이동
	@RequestMapping("/student/eclass/reportModify/{reportCode}")
	public String reportModify(@PathVariable String reportCode, Model model, ReportVO vo, ReportFileVO filevo,
			HttpSession session, ReportSubmissionVO rsubvo) {
		int studentId = (int) session.getAttribute("id");
		vo.setReportCode(reportCode);
		vo.setStudentId(studentId);
		// 과제 제출 내용 조회
		rsubvo = service.selectDetail(vo);
		model.addAttribute("profFile", service.getProfFileInfo(vo));
		model.addAttribute("reportDetail", service.getModDetail(vo));
		return "student/eclass/report/reportModify";
	}

	// 과제 이의 신청 페이지 이동
	@RequestMapping("/student/eclass/reportObjection/{reportCode}")
	public String reportObjection(@PathVariable String reportCode, Model model, HttpSession session, ReportVO vo,
			CourseVO cvo) {
		int studentId = (int) session.getAttribute("id");
		String courseCode = (String) session.getAttribute("courseCode");
		vo.setStudentId(studentId);
		vo.setReportCode(reportCode);
		cvo = (CourseVO) model.getAttribute("courseInfo");
		model.addAttribute("stuobjection", service.selectStuObjection(studentId));
		model.addAttribute("stureportobjectionscore", service.selectStuReportObjection(vo));

		return "student/eclass/report/reportObjection";
	}

	// 과제 이의 신청 처리
	@RequestMapping("/student/eclass/reportObjectionSubmit")
	public String insertReportObjection(ReportObjectionVO vo, HttpSession session) {
		int studentId = (int) session.getAttribute("id");
		vo.setStudentId(studentId);
		service.insertReportObjection(vo);

		return "redirect:/student/eclass/reportList";
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
			String fileUrl = filelocation + "/subreport/" + courseCode + "/" + fileName;
			// 파일객체생성
			File uploadfile = new File(fileUrl);
			uploadfile.getParentFile().mkdirs();

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

	// 과제 수정 처리
	@PostMapping("/student/eclass/reportModify")
	public String reportModify(@RequestParam("file") MultipartFile file, HttpSession session, ReportSubmissionVO vo,
			ReportFileVO filevo) throws IllegalStateException, IOException {
		// 세션 설정
		int studentId = (int) session.getAttribute("id");
		String courseCode = (String) session.getAttribute("courseCode");
		vo.setStudentId(studentId);
		filevo.setStudentId(studentId);
		
		// 파일이 첨부 되었을때
		if (!file.isEmpty()) {
			// 등록된 첨부파일 경로 가져옴.
			String storedFilePath = service.getFile(filevo);
			// 파일 객체 생성후 존재 시 경로를 찾아가 파일 삭제
			File storedFile = new File(storedFilePath);
			// DB에서 파일 삭제
			service.deleteReportFile(filevo);
			if (storedFile.exists()) {
				storedFile.delete();
			}
		}
		
		// 파일이 첨부되었을때 새로 등록하는 과정
		if(!file.isEmpty()) {
		// 원본 파일명 추출
					String fileOriName = file.getOriginalFilename();
					// 확장자 추출
					String fileNameExtension = fileOriName.substring(fileOriName.lastIndexOf("."));
					// 랜덤 파일명 값 생성
					String fileName = UUID.randomUUID().toString().replace("-", "") + fileNameExtension;
					// 파일 URL
					String fileUrl = filelocation + "/subreport/" + courseCode + "/" + fileName;
					// 파일객체생성
					File uploadfile = new File(fileUrl);
					uploadfile.getParentFile().mkdirs();
					
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
					
					// 변경된 파일 코드를 Report_submission 테이블에 업데이트
					service.reportFileCodeUpdate(vo);
		}
		//
		if (file.isEmpty()) {
			// 파일이 첨부 되지 않았을 때

		}

		return "redirect:/student/eclass/reportList";
	}
	
	// 과제 이의신청 상세조회
	@PostMapping("/student/eclass/reportObjectionResult")
	@ResponseBody
	public ReportObjectionVO getReportObjectionDetail(ReportObjectionVO vo) {
		vo = service.getReportObjectionDetail(vo.getReportSubmissionCode());
		
		return vo;
	}

}
