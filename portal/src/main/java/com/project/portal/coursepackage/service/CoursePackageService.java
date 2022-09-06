package com.project.portal.coursepackage.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.project.portal.student.service.StudentVO;


public interface CoursePackageService {

	public List<CoursePackageVO> coursePackage(CoursePackageVO vo);
	
	public CoursePackageVO coursePackageInsert(CoursePackageVO vo);
	
	public List<CoursePackageVO> coursePackageList(CoursePackageVO vo);
	
	public int coursePackageDelete(CoursePackageVO vo);
	
	public int coursePackageAllDelete(CoursePackageVO vo);
	
	public CoursePackageVO coursePackagePoint(CoursePackageVO vo);
	
	public CoursePackageVO studentInfo(CoursePackageVO vo);
	
}
