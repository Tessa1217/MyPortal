package com.project.portal.survey.service;

import org.apache.ibatis.annotations.Mapper;

import com.project.portal.mycourse.service.MyCourseVO;


@Mapper
public interface SurveyMapper {

	// 설문지 조회
	public SurveyVO selectSurvey(SurveyVO vo);
	
	// 설문지 제출
	public void insertSurveyAnswer(SurveyAnswerVO vo);
	
	// 설문 제출 상태 업데이트
	public void updateSurveyState(MyCourseVO vo);
}
