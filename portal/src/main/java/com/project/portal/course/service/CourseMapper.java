package com.project.portal.course.service;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMapper {
	
	CourseVO getWeeklyInfo(CourseVO vo);

}
