package com.project.portal.myquestion.service;

import java.util.List;

public interface myQuestionService {
	// 학생 질문 조회
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
	public myQuestionAnswerVO insertProfMyQuestion(myQuestionAnswerVO vo);
	// 교수 질문 답변 수정
	public myQuestionAnswerVO modifyProfMyQuestion(myQuestionAnswerVO vo);
	
	
	
	
	
}
