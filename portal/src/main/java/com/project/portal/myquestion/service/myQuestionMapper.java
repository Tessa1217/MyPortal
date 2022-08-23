package com.project.portal.myquestion.service;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface myQuestionMapper {
	// 학생 질문 리스트 조회
	public List<myQuestionVO> getStuMyQuestion(String studentId);
	
	
	
	
}
