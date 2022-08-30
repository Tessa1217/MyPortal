package com.project.portal.courseregister.service.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.portal.courseregister.service.RegisterService;
import com.project.portal.courseregister.service.RegisterVO;

/*
 * 작성자: 송도언
 * 작성일자: 2022/08/25
 * 내용: 수강신청(학생정보, 리스트 조회, 신청목록, 신청한 목록 등)에 관한 컨트롤러
 * 수정일자:
 * 수정내용:
*/

@Controller
public class RegisterController {
	
	@Autowired
	RegisterService service;
	
	// 학생 정보, 과목 리스트
	@RequestMapping("/student/courseRegister")
	public String Register(Model model, RegisterVO vo) {
		vo.setStudentId(22000002);
		List<RegisterVO> studentInfo = service.studentInfo(vo);
		model.addAttribute("studentInfo", studentInfo);
		
		List<RegisterVO> registerList = service.registerList(vo);
		model.addAttribute("registerList", registerList);
		
		List<RegisterVO> packageNList = service.packageNList(vo);
		model.addAttribute("packageNList", packageNList);
		return "student/register/register";		
	}
	
	// 강의 신청
	@RequestMapping("/student/registerInsert")
	@ResponseBody
	public int RegisterVO(Model model, RegisterVO vo) {
		vo.setStudentId(22000002);
		return service.registerInsert(vo);
	}
}
