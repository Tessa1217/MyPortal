package com.project.portal.course.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.portal.bachelor.service.BachelorScheduleVO;
import com.project.portal.common.Criteria;
import com.project.portal.survey.service.SurveyVO;

@Mapper
public interface CourseMapper {

	CourseVO getWeeklyInfo(CourseVO vo);

	List<CourseVO> allCourseList(Criteria cri);

	List<CourseVO> surveyList(CourseVO vo);

	SurveyVO surveySelect(SurveyVO vo);

	// 설문지 강의 문항별 평점
	SurveyVO getQuestionAvg(CourseVO vo);

	// 설문지 전체 강의 문항별 평점
	SurveyVO getCourseAvg(CourseVO vo);

	// 설문지 강의 전체 평점
	double myCourseAvg(CourseVO vo);

	// 설문지 전체 강의 전체 평점
	double allMyCourseAvg();
	
	// 현재 학기와 년도 조회
	CourseVO getYearSemester(BachelorScheduleVO vo);

	int getTotal(CourseVO vo);
	int getTotal2(CourseVO vo);

}
