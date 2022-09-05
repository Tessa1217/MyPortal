package com.project.portal.coursepackage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.coursepackage.service.CoursePackageMapper;
import com.project.portal.coursepackage.service.CoursePackageService;
import com.project.portal.coursepackage.service.CoursePackageVO;
import com.project.portal.student.service.StudentVO;


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
		//이전 신청 여부 확인
		CoursePackageVO cVO = mapper.courseCheck(vo);
		if(cVO == null) {		
			mapper.coursePackageInsert(vo);
			return mapper.coursePackageOne(vo);
		}
		return null;
	}

	@Override
	public List<CoursePackageVO> coursePackageList(CoursePackageVO vo) {
		// TODO Auto-generated method stub
		return mapper.coursePackageList(vo);
	}

	@Override
	public int coursePackageDelete(CoursePackageVO vo) {
		// TODO Auto-generated method stub
		return mapper.coursePackageDelete(vo);
	}

	@Override
	public int coursePackageAllDelete(CoursePackageVO vo) {
		// TODO Auto-generated method stub
		return mapper.coursePackageAllDelete(vo);
	}

	@Override
	public CoursePackageVO coursePackagePoint(CoursePackageVO vo) {
		// TODO Auto-generated method stub
		return mapper.coursePackagePoint(vo);
	}

	@Override
	public CoursePackageVO studentInfo(CoursePackageVO vo) {
		// TODO Auto-generated method stub
		return mapper.studentInfo(vo);
	}

	

	
	
	

	

}
