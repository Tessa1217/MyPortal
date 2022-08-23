package com.project.portal.coursepackage.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.portal.course.service.CourseVO;

@Mapper
public interface CoursePackageMapper {

	List<CoursePackageVO> coursePackage(CoursePackageVO vo);
	
	public int coursePackageInsert(CoursePackageVO vo);
	
	public List<CoursePackageVO> coursePackageList(CoursePackageVO vo);
	
	public CoursePackageVO coursePackageOne(CoursePackageVO vo);
}
