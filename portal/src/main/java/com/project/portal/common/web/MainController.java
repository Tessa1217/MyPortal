package com.project.portal.common.web;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.portal.admin.service.AdminService;
import com.project.portal.admin.service.AdminVO;
import com.project.portal.bachelor.service.BachelorNoticeService;
import com.project.portal.bachelor.service.BachelorNoticeVO;
import com.project.portal.bachelor.service.BachelorScheduleService;
import com.project.portal.bachelor.service.BachelorScheduleVO;
import com.project.portal.common.Criteria;
import com.project.portal.common.service.CodeService;
import com.project.portal.common.service.CodeVO;
import com.project.portal.professor.service.ProfessorService;
import com.project.portal.professor.service.ProfessorVO;
import com.project.portal.student.service.StudentService;
import com.project.portal.student.service.StudentVO;

@Controller
// 작성사: 권유진
public class MainController {
	
	// 사용자 
	@Autowired
	StudentService studentService;
	@Autowired
	ProfessorService professorService;
	@Autowired
	AdminService adminService;
	// 코드
	@Autowired
	CodeService codeService;
	// 학사 일정 
	@Autowired
	BachelorScheduleService scheduleService;
	// 학사 공지
	@Autowired
	BachelorNoticeService noticeService;
	
	

	@ModelAttribute("schedule")
	public BachelorScheduleVO getMonth() {
		LocalDate currentdate = LocalDate.now();
		BachelorScheduleVO vo = new BachelorScheduleVO();
		vo.setMonth(currentdate.getMonthValue());
		vo.setYear(currentdate.getYear());
		return vo;
	}

	// 학생 메인 페이지
	@RequestMapping("/student")
	public String home(HttpSession session, BachelorScheduleVO vo, Model model) {
		StudentVO student = new StudentVO();
		student.setStudentId((int) session.getAttribute("id"));
		student = studentService.studentInfoSelect(student);
		session.setAttribute("student", student);
		
		// 수강신청, 수강꾸러미 날짜
		Map<String, BachelorScheduleVO> scheduleMap = new HashMap<String, BachelorScheduleVO>();
		vo.setDetailCode("REG00");
		scheduleMap.put("pkg", scheduleService.getScheduleInfo(vo));
		vo.setDetailCode("REG01");
		scheduleMap.put("reg", scheduleService.getScheduleInfo(vo));
		session.setAttribute("scheduleMap", scheduleMap);
		
		return "student/main";
	}

	// 교수 메인 페이지
	@RequestMapping("/professor")
	public String Phome(HttpSession session, BachelorScheduleVO vo, Model model) {
		ProfessorVO professor = new ProfessorVO();
		professor.setProfessorId((int) session.getAttribute("id"));
		session.setAttribute("professor", professorService.professorInfo(professor));
		
		// 강의 계획서 날짜
		Map<String, BachelorScheduleVO> scheduleMap = new HashMap<String, BachelorScheduleVO>();
		vo.setDetailCode("PROF00");
		scheduleMap.put("yearSemester", scheduleService.getScheduleInfo(vo));
		session.setAttribute("scheduleMap", scheduleMap);
		
		return "professor/main";
	}
	
	// 관리자 메인 페이지
	@RequestMapping("/admin")
	public String Ahome(HttpSession session) {
		AdminVO admin = new AdminVO();
		admin.setAdminId((int) session.getAttribute("id"));
		session.setAttribute("professor", adminService.adminInfoSelect(admin));
		return "admin/main";
	}

	// 이달의 일정
	@RequestMapping({"/student/getMonthly", 
					"/professor/getMonthly",
					"/admin/getMonthly"})
	@ResponseBody
	public List<BachelorScheduleVO> getMonthlySchedule(HttpServletRequest request,
														Model model) {
		BachelorScheduleVO schedule = (BachelorScheduleVO) model.getAttribute("schedule");
		if (request.getRequestURI().contains("student")) {
			schedule.setDetailCode("PROF");
		} else if (request.getRequestURI().contains("professor")) {
			schedule.setDetailCode("STUD");
		}
		return scheduleService.getMonthlyList(schedule);
	}
	
	@RequestMapping({"/student/getNotice",
					"/professor/getNotice",
					"/admin/getNotice"})
	public String getNotice(HttpServletRequest request, 
							Model model, 
							Criteria cri) {
		BachelorNoticeVO notice = new BachelorNoticeVO();
		List<CodeVO> codeList = codeService.getDetailList(null); 
		
		notice.setNoticePrivate("OP");
		if (request.getRequestURI().contains("student")) {
			notice.setNoticeDivision("PROF");
		} else if (request.getRequestURI().contains("professor")) {
			notice.setNoticeDivision("STUD");
		}
		model.addAttribute("noticeList", noticeService.getNoticeList(notice, cri));
		return "layout/fragments/common/notice :: #noticeFragment";
	}

}
