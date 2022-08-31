package com.project.portal.survey.service;

import com.project.portal.course.service.CourseVO;

public interface SurveyService {
	// 설문지 조회
	public SurveyVO selectSurvey(SurveyVO vo);
	
	// 설문지 제출
	public void insertSurveyAnswer(SurveyAnswerVO vo);
	
	// 설문지 통계
	public SurveyAvgVO getSurveyStats(CourseVO course, SurveyAvgVO vo);
}
