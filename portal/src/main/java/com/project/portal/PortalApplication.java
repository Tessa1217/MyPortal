package com.project.portal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.professor.service.ProfessorVO;
import com.project.portal.professor.service.impl.ProfessorServiceImpl;
import com.project.portal.student.service.StudentVO;
import com.project.portal.student.service.impl.StudentServiceImpl;

@SpringBootApplication
@Controller
@EnableScheduling
public class PortalApplication {
	
	@Autowired 
	StudentServiceImpl studentService;
	@Autowired
	ProfessorServiceImpl professorService;
//	@Autowired
//	AdminServiceImpl adminService;
	
	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
	}
	
	// 학생 메인 페이지로 이동 시 세션에 학생 정보가 담긴 VO 객체 저장 
	@RequestMapping("/student")
	public String home(HttpSession session) {
		StudentVO student = new StudentVO();
		student.setStudentId((int) session.getAttribute("id"));
		student = studentService.studentInfoSelect(student);
		System.out.println(student);
		session.setAttribute("student", student);
		return "student/main";
	}
	
	// 교수 메인 페이지로 이동 시 세션에 교수 정보가 담긴 VO 객체 저장 
	@RequestMapping("/professor")
	public String Phome(HttpSession session) {
		ProfessorVO professor = new ProfessorVO();
		professor.setProfessorId((int) session.getAttribute("id"));
		professor = professorService.professorInfo(professor);
		session.setAttribute("professor", professor);
		return "professor/main";
	}
	
	// 관리자 VO 생성 필요
	@RequestMapping("/admin")
	public String Ahome(HttpSession session) {
		return "admin/main";
	}

}