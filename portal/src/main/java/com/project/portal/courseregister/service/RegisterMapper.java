package com.project.portal.courseregister.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.portal.course.service.CourseVO;
import com.project.portal.coursepackage.service.CoursePackageVO;

@Mapper
public interface RegisterMapper {
	
	public List<RegisterVO> studentInfo(RegisterVO vo);

	public List<RegisterVO> registerList(RegisterVO vo);
	
	public int registerInsert(RegisterVO vo);
	
	public List<RegisterVO> packageNList(RegisterVO vo);
}
