package com.project.portal.professor.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.portal.common.Criteria;
import com.project.portal.professor.service.ProfessorService;
import com.project.portal.professor.service.ProfessorVO;
import com.project.portal.tempcourse.web.TempcourseController;

// 작성자: 김진형
@Controller
public class ProfessorController {

	private static final Logger logger = LoggerFactory.getLogger(TempcourseController.class);
	
	@Autowired ProfessorService service;
	
	//교수 전체 조회(관리자)
	@RequestMapping("/admin/professorList")
	public String ProfessorList(ProfessorVO vo, Model model, Criteria cri) {
		model.addAttribute("professorList", service.professorList(cri));
		return "admin/info/professorList";
	}
	
	//교수 본인 정보 조회
	@RequestMapping("/professor/professorInfo")
	public String Professor(ProfessorVO vo, Model model) {
		model.addAttribute("professorInfo", service.professorInfo(vo));
		return "professor/info/personal";
	}
	
	//교수 정보 수정
	@RequestMapping("/professor/professorInfoUpdate")
	@ResponseBody
	public ProfessorVO professorInfoUpdate(ProfessorVO vo, Model model) {
		return service.professorInfoUpdate(vo);
	}
	
}