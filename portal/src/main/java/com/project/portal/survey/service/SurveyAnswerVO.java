package com.project.portal.survey.service;

import lombok.Data;

@Data
public class SurveyAnswerVO {
	
	private String courseCode; //강의코드
	private int studentId; // 학번
	//설문답변 1 ~ 10
	private int surveyA1;
	private int surveyA2;
	private int surveyA3;
	private int surveyA4;
	private int surveyA5;
	private int surveyA6;
	private int surveyA7;
	private int surveyA8;
	private int surveyA9;
	private int surveyA10;
}
