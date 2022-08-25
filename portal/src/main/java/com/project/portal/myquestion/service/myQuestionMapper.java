package com.project.portal.myquestion.service;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface myQuestionMapper {
	// 학생 질문 리스트 조회
	public List<myQuestionVO> getStuMyQuestion(String studentId);
	// 학생 질문 답변여부
	public myQuestionVO getStuMyQuestionCheck(String studentId);
	
	
	// 교수 질문 조회
	public List<myQuestionVO> getProfMyQuestion(String courseCode);
	
	
	
	// 학생 질문 상세 조회
	public myQuestionVO getStuMyQuestionDetail(String questionNum);
	// 교수 질문 상세 조회
	public myQuestionVO getProfMyQuestionDetail(String questionNum);
	// 교수 질문 답변 등록
	public myQuestionAnswerVO insertProfMyQuestion(String questionNum);
	
}
