package com.project.portal.survey.service;

import com.project.portal.mycourse.service.MyCourseVO;

public interface SurveyService {
	// 설문지 조회
	public SurveyVO selectSurvey(SurveyVO vo);
	
	// 설문지 제출
	public void insertSurveyAnswer(SurveyAnswerVO vo);
	
	// 설문 제출 상태 업데이트
	public void updateSurveyState(MyCourseVO vo);
	
	// 학생 설문 상태 조회
	public MyCourseVO selectSurveyState(MyCourseVO vo);
}