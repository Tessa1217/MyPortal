package com.project.portal.survey.service;


public interface SurveyService {
	// 설문지 조회
	public SurveyVO selectSurvey(SurveyVO vo);
	
	// 설문지 제출
	public void insertSurveyAnswer(SurveyAnswerVO vo);
	
}
