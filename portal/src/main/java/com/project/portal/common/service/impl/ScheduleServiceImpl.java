package com.project.portal.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.portal.common.service.ScheduleMapper;
import com.project.portal.common.service.ScheduleService;
import com.project.portal.course.service.CourseVO;

public class ScheduleServiceImpl implements ScheduleService {
	
	@Autowired
	ScheduleMapper mapper;
	
	@Override
	public void updateExamScore(CourseVO vo) {
		mapper.updateExamScore(vo);
	}
	
	

}
