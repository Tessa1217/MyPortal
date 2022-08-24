package com.project.portal.courseregister.service.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.portal.courseregister.service.RegisterService;
import com.project.portal.courseregister.service.RegisterVO;

@Controller
public class RegisterController {

	@Autowired
	RegisterService service;
	
	// 학생 정보, 과목 리스트
	@RequestMapping("/student/courseRegister")
	public String Register(Model model, RegisterVO vo) {
		vo.setStudentId(22000001);
		List<RegisterVO> studentInfo = service.studentInfo(vo);
		model.addAttribute("studentInfo", studentInfo);
		
		List<RegisterVO> registerList = service.registerList(vo);
		model.addAttribute("registerList", registerList);
		return "student/register/register";		
	}
	
	// 강의 담아놓기
	@RequestMapping("/student/registerInsert")
	@ResponseBody
	public int RegisterVO(Model model, RegisterVO vo) {
		vo.setStudentId(22000001);
		return service.registerInsert(vo);
	}
}
