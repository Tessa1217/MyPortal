package com.project.portal.myquestion.service;

import java.util.Date;

import lombok.Data;

@Data
public class myQuestionAnswerVO {
	private String lectureAnswerNum; // 답변번호
	private String lectureQuestionNum; // 질문번호
	private String lectureAnswerContent; // 답변내용
	private Date lectureAnswerRegDate; // 답변 등록일
	private Date lectureAnswerModDate; // 답변 수정일
}
