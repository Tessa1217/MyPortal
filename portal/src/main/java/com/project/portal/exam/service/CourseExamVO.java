package com.project.portal.exam.service;

import lombok.Data;

@Data
public class CourseExamVO {
	private String examQuestionNum;
	private int examQuestionPoint;
	private String examCode;
	private String questionCode;
	private String examComplete;
	private QuestionVO examQuestion;
}
