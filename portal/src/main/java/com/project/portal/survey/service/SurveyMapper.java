package com.project.portal.survey.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SurveyMapper {

	// 설문지 조회
	public SurveyVO selectSurvey(SurveyVO vo);
	
	// 설문지 제출
	public SurveyAnswerVO insertSurveyAnswer(SurveyAnswerVO vo);
}
