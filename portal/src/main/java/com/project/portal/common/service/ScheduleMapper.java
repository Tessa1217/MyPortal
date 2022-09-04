package com.project.portal.common.service;

import org.apache.ibatis.annotations.Mapper;

import com.project.portal.course.service.CourseVO;

@Mapper
public interface ScheduleMapper {
	void updateExamScore(CourseVO vo);
	
}
