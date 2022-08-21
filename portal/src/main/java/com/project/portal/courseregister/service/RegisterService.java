package com.project.portal.courseregister.service;

import java.util.List;

import com.project.portal.course.service.CourseVO;

public interface RegisterService {
	//강의 목록 조회
	public List<CourseVO> registerList(CourseVO vo);
}
