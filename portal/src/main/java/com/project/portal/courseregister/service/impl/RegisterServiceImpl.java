package com.project.portal.courseregister.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.course.service.CourseVO;
import com.project.portal.courseregister.service.RegisterMapeer;
import com.project.portal.courseregister.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	RegisterMapeer mapper;
	
	@Override
	public List<CourseVO> registerList(CourseVO vo) {
		// TODO Auto-generated method stub
		return mapper.registerList(vo);
	}

}
