package com.project.portal.course.service;

import java.util.List;

import com.project.portal.common.Criteria;

public interface CourseService {
	
	CourseVO getWeeklyInfo(CourseVO vo);

	List<CourseVO> allCourseList(Criteria cri);

	List<CourseVO> surveyList(Criteria cri);

}
