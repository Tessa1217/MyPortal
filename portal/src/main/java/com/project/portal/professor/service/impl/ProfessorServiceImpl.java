package com.project.portal.professor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.professor.service.ProfessorMapper;
import com.project.portal.professor.service.ProfessorService;
import com.project.portal.professor.service.ProfessorVO;

@Service
public class ProfessorServiceImpl implements ProfessorService {
	
	@Autowired ProfessorMapper mapper;

	@Override
	public ProfessorVO professorInfo(ProfessorVO vo) {
		// TODO Auto-generated method stub
		return mapper.professorInfo(vo);
	}
}
