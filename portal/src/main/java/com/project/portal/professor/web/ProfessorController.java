package com.project.portal.professor.web;

import org.mybatis.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.professor.service.ProfessorService;
import com.project.portal.professor.service.ProfessorVO;

@Controller
public class ProfessorController {

//	private static final Logger logger = LoggerFactory.getLogger(TempcourseController.class);
	
	@Autowired ProfessorService service;
	
	//교수 본인 정보 조회
	@RequestMapping("/professor/professorInfo")
	public String Professor(ProfessorVO vo, Model model) {
		model.addAttribute("professorInfo", service.professorInfo(vo));
		return "professor/info/personal";
	}
}
