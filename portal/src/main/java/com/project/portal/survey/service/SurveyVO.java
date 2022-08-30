package com.project.portal.survey.service;

import lombok.Data;

@Data
public class SurveyVO {
	
	private String courseCode;
	//질문 내용 1~10
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
	
	private double a1Avg;
	private double a2Avg;
	private double a3Avg;
	private double a4Avg;
	private double a5Avg;
	private double a6Avg;
	private double a7Avg;
	private double a8Avg;
	private double a9Avg;
	private double a10Avg;

}
