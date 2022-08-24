package com.project.portal.courseregister.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.course.service.CourseVO;
import com.project.portal.coursepackage.service.CoursePackageVO;
import com.project.portal.courseregister.service.RegisterMapper;
import com.project.portal.courseregister.service.RegisterService;
import com.project.portal.courseregister.service.RegisterVO;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	RegisterMapper mapper;
	
	@Override
	public List<RegisterVO> studentInfo(RegisterVO vo) {
		// TODO Auto-generated method stub
		return mapper.studentInfo(vo);
	}

	@Override
	public List<RegisterVO> registerList(RegisterVO vo) {
		// TODO Auto-generated method stub
		return mapper.registerList(vo);
	}

	@Override
	public int registerInsert(RegisterVO vo) {
		// TODO Auto-generated method stub
		return mapper.registerInsert(vo);
	}

	
}
