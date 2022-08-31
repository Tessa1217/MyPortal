package com.project.portal.survey.service;

import org.apache.ibatis.annotations.Mapper;

import com.project.portal.course.service.CourseVO;


@Mapper
public interface SurveyMapper {

	// 설문지 조회
	public SurveyVO selectSurvey(SurveyVO vo);
	
	// 설문지 제출
	public void insertSurveyAnswer(SurveyAnswerVO vo);
	//////////////////////////////	
	// 설문지 강의 문항별 평점
	SurveyVO getQuestionAvg(CourseVO vo);
	
	// 설문지 전체 강의 문항별 평점
	SurveyVO getCourseAvg(CourseVO vo);
	
	// 설문지 강의 전체 평점
	double myCourseAvg(CourseVO vo);
	
	// 설문지 전체 강의 전체 평점
	double allMyCourseAvg();
}
