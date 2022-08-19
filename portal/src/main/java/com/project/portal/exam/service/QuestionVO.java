package com.project.portal.exam.service;

import java.util.List;

import lombok.Data;

@Data
public class QuestionVO {
	private String questionCode;
	private String questionContent;
	private int questionAnswer;
	
	// 문제 문항 정보
	private List<QuestionOptionVO> optionList;
}
