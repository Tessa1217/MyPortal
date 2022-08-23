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

	@Override
	public CoursePackageVO coursePackageInsert(CoursePackageVO vo) {
		// TODO Auto-generated method stub
		 mapper.coursePackageInsert(vo);
		return mapper.coursePackageOne(vo);
	}

	@Override
	public List<CoursePackageVO> coursePackageList(CoursePackageVO vo) {
		// TODO Auto-generated method stub
		return mapper.coursePackageList(vo);
	}

	

	
	
	

	

}
