package com.project.portal.courseregister.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.portal.course.service.CourseVO;

@Mapper
public interface RegisterMapeer {

	List<CourseVO> registerList(CourseVO vo);
}
