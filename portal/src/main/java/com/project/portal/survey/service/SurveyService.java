package com.project.portal.survey.service;

import java.util.List;

public interface SurveyService {
	// 설문지 조회
	public SurveyVO selectSurvey(SurveyVO vo);
	
	// 설문지 제출
	public SurveyAnswerVO insertSurveyAnswer(SurveyAnswerVO vo);
}
