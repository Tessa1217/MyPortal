package com.project.portal.professor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.common.Criteria;
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

	@Override
	public List<ProfessorVO> professorList(ProfessorVO vo) {
		// TODO Auto-generated method stub
		return mapper.professorList(vo);
	}

	@Override
	public ProfessorVO professorInfoUpdate(ProfessorVO vo) {
		// TODO Auto-generated method stub
		mapper.professorInfoUpdate(vo);
		return mapper.professorInfo(vo);
	}

	@Override
	public ProfessorVO adminProfessorInfoSelect(ProfessorVO vo) {
		// TODO Auto-generated method stub
		return mapper.adminProfessorInfoSelect(vo);
	}

	@Override
	public int getTotal(ProfessorVO vo) {
		// TODO Auto-generated method stub
		return mapper.getTotal(vo);
	}
}
