package com.project.portal.professor.service;

import java.util.List;

import com.project.portal.common.Criteria;

public interface ProfessorService {
	
	public ProfessorVO professorInfo(ProfessorVO vo);

	public List<ProfessorVO> professorList(ProfessorVO vo);

	public ProfessorVO professorInfoUpdate(ProfessorVO vo);

	public ProfessorVO adminProfessorInfoSelect(ProfessorVO vo);
	
	int getTotal(ProfessorVO vo);
}
