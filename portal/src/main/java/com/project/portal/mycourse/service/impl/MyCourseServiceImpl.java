package com.project.portal.mycourse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.mycourse.service.MyCourseMainVO;
import com.project.portal.mycourse.service.MyCourseMapper;
import com.project.portal.mycourse.service.MyCourseService;
import com.project.portal.mycourse.service.MyCourseVO;


@Service
public class MyCourseServiceImpl implements MyCourseService {
	
	@Autowired
	MyCourseMapper mapper;

	@Override
	public List<MyCourseVO> getstuMyCourse(MyCourseVO vo) {
		// TODO Auto-generated method stub
		return mapper.getstuMyCourse(vo);
	}

	@Override
	public MyCourseMainVO getstuMyCoursePage(String courseCode) {
		// TODO Auto-generated method stub
		return mapper.getstuMyCoursePage(courseCode);
	}



}
