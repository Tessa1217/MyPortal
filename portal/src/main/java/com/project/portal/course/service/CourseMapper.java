package com.project.portal.course.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.portal.common.Criteria;

@Mapper
public interface CourseMapper {
	
	CourseVO getWeeklyInfo(CourseVO vo);

	List<CourseVO> allCourseList(Criteria cri);

	List<CourseVO> surveyList(Criteria cri);

}
