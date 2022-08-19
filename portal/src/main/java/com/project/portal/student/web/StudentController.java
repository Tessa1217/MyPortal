package com.project.portal.student.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.common.Criteria;
import com.project.portal.student.service.StudentService;
import com.project.portal.student.service.StudentVO;
import com.project.portal.tempcourse.web.TempcourseController;

@Controller
public class StudentController {

	private static final Logger logger = LoggerFactory.getLogger(TempcourseController.class);
	
	@Autowired StudentService service;
	
	//학생 전체 조회(관리자)
	@RequestMapping("/admin/studentList")
	public String Student(StudentVO vo, Model model, Criteria cri) {
		model.addAttribute("studentList", service.studentList(cri));
		return "admin/info/studentList";
	}
	//학생 개인 조회(학생)
	@RequestMapping("/student/studentInfoSelect")
	public String Student(StudentVO vo, Model model) {
		model.addAttribute("studentInfoSelect", service.studentInfoSelect(vo));
		return "student/info/personal";
	}
}
