package com.project.portal.mycourse.service;

import java.util.List;

import com.project.portal.common.Criteria;

public interface MyCourseService {
	//학생 수강 조회
	public List<MyCourseVO> getstuMyCourse(MyCourseVO vo);
	
	//교수 강의 목록 조회
	public List<myProfCourseVO> getProfMyCourse(myProfCourseVO vo);
	
	//학생 수강강의 페이지 이동
	public MyCourseMainVO getstuMyCoursePage(String courseCode);
	//교수 수강강의 페이지 이동
	public MyCourseMainVO getProfMyCoursePage(String courseCode);

	// 수강강의 계획서 조회
	public myCourseDetailVO getstuMyCourseDetail(String courseCode);
	
	// 수강강의 주차계획 조회
	public List<myCourseDetailVO> getstuMyCourseWeekDetail(String courseCode);

	public List<MyCourseVO> studentStudyList(Criteria cri);

	public MyCourseVO studentCreditSum(MyCourseVO vo);

	public List<MyCourseVO> studentSortationCredit(MyCourseVO vo);


	

}
