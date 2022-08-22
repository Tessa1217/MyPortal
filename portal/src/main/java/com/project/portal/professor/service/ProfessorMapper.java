package com.project.portal.professor.service;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProfessorMapper {
	public ProfessorVO professorInfo(ProfessorVO vo);
	}