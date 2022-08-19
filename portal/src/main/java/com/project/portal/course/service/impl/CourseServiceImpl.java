package com.project.portal.course.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.course.service.CourseMapper;
import com.project.portal.course.service.CourseService;
import com.project.portal.course.service.CourseVO;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	CourseMapper mapper;
	
	@Override
	public CourseVO getWeeklyInfo(CourseVO vo) {
		return mapper.getWeeklyInfo(vo);
	};

}
