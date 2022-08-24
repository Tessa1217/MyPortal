package com.project.portal.professor.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.professor.service.ProfessorService;
import com.project.portal.professor.service.ProfessorVO;
import com.project.portal.tempcourse.web.TempcourseController;

@Controller
public class ProfessorController {

	private static final Logger logger = LoggerFactory.getLogger(TempcourseController.class);
	
	@Autowired ProfessorService service;
	
	//교수 본인 정보 조회
	@RequestMapping("/professor/professorInfo")
	public String Professor(ProfessorVO vo, Model model) {
		model.addAttribute("professorInfo", service.professorInfo(vo));
		return "professor/info/personal";
	}
}
