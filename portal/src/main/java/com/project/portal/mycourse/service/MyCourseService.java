package com.project.portal.mycourse.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.portal.course.service.CourseVO;
import com.project.portal.exam.service.ExamInfoVO;
import com.project.portal.exam.service.ExamScoreVO;

public interface MyCourseService {
	//학생 수강 조회
	public List<MyCourseVO> getstuMyCourse(MyCourseVO myCourse, CourseVO vo);
	
	//교수 강의 목록 조회
	public List<myProfCourseVO> getProfMyCourse(CourseVO vo);
	
	//학생 수강강의 페이지 이동
	public MyCourseMainVO getstuMyCoursePage(String courseCode);
	//교수 수강강의 페이지 이동
	public MyCourseMainVO getProfMyCoursePage(String courseCode);

	// 수강강의 계획서 조회
	public myCourseDetailVO getstuMyCourseDetail(String courseCode);
	
	// 수강강의 주차계획 조회
	public List<myCourseDetailVO> getstuMyCourseWeekDetail(String courseCode);

	public List<MyCourseVO> studentStudyList(MyCourseVO vo);

	public MyCourseVO studentCreditSum(MyCourseVO vo);

	public List<MyCourseVO> studentSortationCredit(MyCourseVO vo);

	public List<MyCourseVO> semesterGradeSelect(MyCourseVO vo);

	// 수강생 리스트 조회
	List<ExamScoreVO> getStudentList(ExamInfoVO vo);
	

}
