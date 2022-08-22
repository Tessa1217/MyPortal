package com.project.portal.mycourse.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyCourseMapper {
	//학생 수강 조회
	public List<MyCourseVO> getstuMyCourse(MyCourseVO vo);
	
	// 수강강의 페이지 이동
	public MyCourseMainVO getstuMyCoursePage(String courseCode);

}
