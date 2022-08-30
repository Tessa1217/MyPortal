package com.project.portal.course.service;

import java.util.List;

import com.project.portal.common.Criteria;
import com.project.portal.survey.service.SurveyVO;

public interface CourseService {
	
	CourseVO getWeeklyInfo(CourseVO vo);

	List<CourseVO> allCourseList(Criteria cri);

	List<CourseVO> surveyList(CourseVO vo);

	SurveyVO surveySelect(SurveyVO vo);

//	List<CourseVO> surveyAvg(CourseVO vo);

}
