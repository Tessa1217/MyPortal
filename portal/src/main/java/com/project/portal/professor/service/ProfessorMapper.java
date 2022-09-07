package com.project.portal.professor.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.portal.common.Criteria;

@Mapper
public interface ProfessorMapper {
	public ProfessorVO professorInfo(ProfessorVO vo);

	public List<ProfessorVO> professorList(Criteria cri);

	public int professorInfoUpdate(ProfessorVO vo);

	public ProfessorVO adminProfessorInfoSelect(ProfessorVO vo);
	}