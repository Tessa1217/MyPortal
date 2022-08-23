package com.project.portal.myquestion.service;

import java.util.List;

public interface myQuestionService {
	// 학생 질문 조회
	public List<myQuestionVO> getStuMyQuestion(String studentId);
	
	// 질문 답변 조회
	
}
