package com.project.portal.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.common.service.ScheduleMapper;
import com.project.portal.common.service.ScheduleService;
import com.project.portal.course.service.CourseVO;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	
	@Autowired
	ScheduleMapper mapper;
	
	@Override
	public void updateExamScore(CourseVO vo) {
		mapper.updateExamScore(vo);
	}
	
	

}
