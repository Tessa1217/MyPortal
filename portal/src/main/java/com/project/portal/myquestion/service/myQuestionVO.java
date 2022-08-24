package com.project.portal.myquestion.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class myQuestionVO {
	
	private String lectureQuestionNum; // 질문번호
	private String lectureQuestionTitle; // 질문제목
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lectureQuestionRegDate; // 질문 등록일
	private String weekCode; // 강의주차 계획코드
	private String lectureQuestionAnswerCheck; // 답변 여부
	private String lectureQuestionContent; // 질문 내용
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lectureAnswerRegDate; // 답변등록일
	private String lectureAnswerContent; // 답변 내용 
	private String studentId; // 학번
	private String weekNum;// 주차
	private String searchType; // 검색타입
	
	
}
