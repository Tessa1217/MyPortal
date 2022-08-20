package com.project.portal.exam.service;

import lombok.Data;

@Data
public class StudentExamVO {
	private String examCode;
	private String examQuestionNum;
	private int studentQuestionNum;
	private int studentQuestionAnswer;
	private int studentRightOption;
	private int studentId;

}
