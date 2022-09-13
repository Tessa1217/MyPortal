package com.project.portal.course.service;

import java.util.List;

import com.project.portal.bachelor.service.BachelorScheduleVO;
import com.project.portal.common.Criteria;
import com.project.portal.survey.service.SurveyAvgVO;
import com.project.portal.survey.service.SurveyVO;

public interface CourseService {
	
	CourseVO getWeeklyInfo(CourseVO vo);

	List<CourseVO> allCourseList(Criteria cri);

	List<CourseVO> surveyList(CourseVO vo);

	SurveyVO surveySelect(SurveyVO vo);
	
	// 설문지 통계
	SurveyAvgVO getSurveyStats(CourseVO course, SurveyAvgVO vo);

	// 이번 년도와 학기 조회
	CourseVO getYearSemester(BachelorScheduleVO vo);

	int getTotal(CourseVO vo);

	int getTotal2(CourseVO vo);

}
