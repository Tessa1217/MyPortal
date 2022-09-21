package com.project.portal.survey.service;

import lombok.Data;

@Data
public class SurveyVO {
	
	private String courseCode;
	//질문 내용 1 ~ 10
	private String surveyQ1;
	private String surveyQ2;
	private String surveyQ3;
	private String surveyQ4;
	private String surveyQ5;
	private String surveyQ6;
	private String surveyQ7;
	private String surveyQ8;
	private String surveyQ9;
	private String surveyQ10;
	
	//질문 답안 평균값
	private double A1Avg;
	private double A2Avg;
	private double A3Avg;
	private double A4Avg;
	private double A5Avg;
	private double A6Avg;
	private double A7Avg;
	private double A8Avg;
	private double A9Avg;
	private double A10Avg;

}
