package com.project.portal.courseregister.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.portal.course.service.CourseVO;
import com.project.portal.coursepackage.service.CoursePackageVO;

@Mapper
public interface RegisterMapper {
	
	//학생정보
	public List<RegisterVO> studentInfo(RegisterVO vo);

	//강의목록
	public List<RegisterVO> registerList(RegisterVO vo);
	
	//강의 신청
	public int registerInsert(RegisterVO vo);
	
	//수강꾸러미 실패 불러오기
	public List<RegisterVO> packageNList(RegisterVO vo);
	
	//신청 성공 강의 띄우기
	public List<RegisterVO> successList(RegisterVO vo);
	
	public RegisterVO regSuccess(RegisterVO vo);
	
//	public List<RegisterVO> limitCount(RegisterVO vo);
	
}
