package com.project.portal.courseregister.service;

import java.util.List;

import com.project.portal.course.service.CourseVO;
import com.project.portal.coursepackage.service.CoursePackageVO;

public interface RegisterService {
	
	//학생 정보 조회
	public List<RegisterVO> studentInfo(RegisterVO vo);
	
	//강의 목록 조회
	public List<RegisterVO> registerList(RegisterVO vo);
	
	//강의 담기
	public int registerInsert(RegisterVO vo);
}
