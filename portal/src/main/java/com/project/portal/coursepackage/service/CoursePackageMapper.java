package com.project.portal.coursepackage.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Mapper;

import com.project.portal.course.service.CourseVO;
import com.project.portal.student.service.StudentVO;

@Mapper
public interface CoursePackageMapper {

	List<CoursePackageVO> coursePackage(CoursePackageVO vo);
	
	public int coursePackageInsert(CoursePackageVO vo);
	
	public List<CoursePackageVO> coursePackageList(CoursePackageVO vo);
	
	public CoursePackageVO coursePackageOne(CoursePackageVO vo);
	
	public int coursePackageDelete(CoursePackageVO vo);
	
	public int coursePackageAllDelete(CoursePackageVO vo);
	
	public CoursePackageVO coursePackagePoint(CoursePackageVO vo);
	
	public CoursePackageVO courseCheck(CoursePackageVO vo);
	
	public CoursePackageVO studentInfo(CoursePackageVO vo);
}
