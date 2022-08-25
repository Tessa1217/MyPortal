package com.project.portal.myquestion.service;

import java.util.Date;

import lombok.Data;

@Data
public class myQuestionAnswerVO {
	private String lectureAnswerNum;
	private String lectureQuestionNum;
	private String lectureAnswerContent;
	private Date lectureAnswerRegDate;
	private Date lectureAnswerModDate;
}
