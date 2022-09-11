package com.project.portal.myquestion.service;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.portal.common.Criteria;
@Mapper
public interface myQuestionMapper {
	// 학생 질문 조회
	public List<myQuestionVO> getStuMyQuestion(@Param("myQuestion") myQuestionVO vo, @Param("cri") Criteria cri);
	// 학생 질문 개수
	public int getStuTotal(myQuestionVO vo);
	// 학생 질문 답변여부
	public myQuestionVO getStuMyQuestionCheck(String studentId);
	
	
	// 교수 질문 조회
	public List<myQuestionVO> getProfMyQuestion(@Param("myQuestion") myQuestionVO vo , @Param("cri") Criteria cri);
	//교수 질문 개수
	public int getProfTotal(myQuestionVO vo);
	// 학생 질문 상세 조회
	public myQuestionVO getStuMyQuestionDetail(String questionNum);
	// 교수 질문 상세 조회
	public myQuestionVO getProfMyQuestionDetail(String questionNum);
	// 교수 질문 답변 등록
	public void insertProfMyQuestion(myQuestionAnswerVO vo);
	// 교수 질문 답변 수정
	public void modifyProfMyQuestion(myQuestionAnswerVO vo);
	
}
