package com.project.portal.mycourse.service;

import java.util.List;

public interface MyCourseService {
	//학생 수강 조회
	public List<MyCourseVO> getstuMyCourse(MyCourseVO vo);
}
