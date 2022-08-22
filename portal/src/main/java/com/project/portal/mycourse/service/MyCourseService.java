package com.project.portal.mycourse.service;

import java.util.List;

public interface MyCourseService {
	//학생 수강 조회
	public List<MyCourseVO> getstuMyCourse(MyCourseVO vo);
	
	// 수강강의 페이지 이동
		public MyCourseMainVO getstuMyCoursePage(String courseCode);
}
