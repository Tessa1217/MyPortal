package com.project.portal.coursepackage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.coursepackage.service.CoursePackageMapper;
import com.project.portal.coursepackage.service.CoursePackageService;
import com.project.portal.coursepackage.service.CoursePackageVO;


@Service
public class CoursePackageServiceImpl implements CoursePackageService {
	
	@Autowired
	CoursePackageMapper mapper;

	@Override
	public List<CoursePackageVO> coursePackage(CoursePackageVO vo) {
		// TODO Auto-generated method stub
		return mapper.coursePackage(vo);
	}

	
	
	

	

}
