package com.project.portal.exam.service;

import java.util.List;

import lombok.Data;

@Data
public class ExamResultVO {
	private String examQuestionNum;
	private String examCode;
	private int examQuestionPoint;
	private String questionCode;
	private String questionContent;
	private int questionAnswer;
	private int studentQuestionNum;
	private int studentQuestionAnswer;
	private int studentRightOption;
	private int studentId;
	private String studentName;
	private List<QuestionOptionVO> optionList;
}
