package com.project.portal.professor.service;

import java.util.List;

import com.project.portal.common.Criteria;

public interface ProfessorService {
	
	public ProfessorVO professorInfo(ProfessorVO vo);

	public List<ProfessorVO> professorList(Criteria cri);
}
