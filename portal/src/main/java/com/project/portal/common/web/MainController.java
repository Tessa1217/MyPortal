package com.project.portal.common.web;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.portal.admin.service.AdminService;
import com.project.portal.admin.service.AdminVO;
import com.project.portal.bachelor.service.BachelorScheduleService;
import com.project.portal.bachelor.service.BachelorScheduleVO;
import com.project.portal.professor.service.ProfessorService;
import com.project.portal.professor.service.ProfessorVO;
import com.project.portal.student.service.StudentService;
import com.project.portal.student.service.StudentVO;

@Controller
// 작성사: 권유진
public class MainController {

	@Autowired
	StudentService studentService;
	@Autowired
	ProfessorService professorService;
	@Autowired
	BachelorScheduleService scheduleService;
	@Autowired
	AdminService adminService;

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
	public String home(HttpSession session) {
		StudentVO student = new StudentVO();
		student.setStudentId((int) session.getAttribute("id"));
		student = studentService.studentInfoSelect(student);
		session.setAttribute("student", student);
		return "student/main";
	}

	// 교수 메인 페이지
	@RequestMapping("/professor")
	public String Phome(HttpSession session, Model model) {
		ProfessorVO professor = new ProfessorVO();
		professor.setProfessorId((int) session.getAttribute("id"));
		session.setAttribute("professor", professorService.professorInfo(professor));
		return "professor/main";
	}
	
	// 관리자 VO 생성 필요
	@RequestMapping("/admin")
	public String Ahome(HttpSession session) {
		AdminVO admin = new AdminVO();
		admin.setAdminId((int) session.getAttribute("id"));
		session.setAttribute("professor", adminService.adminInfoSelect(admin));
		return "admin/main";
	}

	@RequestMapping("/getMonthly")
	@ResponseBody
	public List<BachelorScheduleVO> getMonthlySchedule(Model model) {
		BachelorScheduleVO schedule = (BachelorScheduleVO) model.getAttribute("schedule");
		schedule.setDetailCode("STUD");
		return scheduleService.getMonthlyList(schedule);
	}


}
