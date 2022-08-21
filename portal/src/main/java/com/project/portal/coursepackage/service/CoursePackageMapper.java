package com.project.portal.coursepackage.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CoursePackageMapper {

	List<CoursePackageVO> coursePackage(CoursePackageVO vo);
}
