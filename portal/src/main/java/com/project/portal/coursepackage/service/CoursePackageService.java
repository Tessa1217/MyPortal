package com.project.portal.coursepackage.service;

import java.util.List;

import com.project.portal.course.service.CourseVO;


public interface CoursePackageService {

	public List<CoursePackageVO> coursePackage(CoursePackageVO vo);
	
	public CoursePackageVO coursePackageInsert(CoursePackageVO vo);
	
	public List<CoursePackageVO> coursePackageList(CoursePackageVO vo);
	
	public int coursePackageDelete(CoursePackageVO vo);
	
	
}
