package com.project.portal.courseregister.service.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.course.service.CourseVO;
import com.project.portal.courseregister.service.RegisterService;

@Controller
public class RegisterController {

	@Autowired
	RegisterService service;
	
	@RequestMapping("/student/courseRegister")
	public String Register(Model model, CourseVO vo) {
		
		List<CourseVO> registerList = service.registerList(vo);
		return "student/register/register";
	}
}
