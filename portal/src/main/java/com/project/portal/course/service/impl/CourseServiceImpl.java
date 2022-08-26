package com.project.portal.course.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.common.Criteria;
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
	}

	@Override
	public List<CourseVO> allCourseList(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.allCourseList(cri);
	};

}
