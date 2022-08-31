package com.project.portal.survey.service;

import lombok.Data;

@Data
public class SurveyAvgVO {
	
	// 개인 문항별 강의 평점 평균
	private SurveyVO questionAvg;
	
	// 강의 전체 문항별 강의 평점 평균
	private SurveyVO courseAvg;
	
	// 개인 전체 강의 평점 평균
	private double myCourseAvg;
	
	// 학교 전체 강의 평점 평균
	private double allMyCourseAvg;
	
}
