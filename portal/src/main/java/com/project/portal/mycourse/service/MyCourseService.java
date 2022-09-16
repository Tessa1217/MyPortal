package com.project.portal.mycourse.service;

import java.util.List;
import java.util.Map;

import com.project.portal.course.service.CourseVO;
import com.project.portal.exam.service.ExamInfoVO;
import com.project.portal.exam.service.ExamScoreVO;
import com.project.portal.studynotice.service.StudyNoticeVO;

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
	// 강의명 조회
	public String getCourseName(String courseCode);
	// 이주 게획 조회
	public Map<String, Object> getWeeklyList(CourseVO vo);
	// 최근 강의 공지사항 조회
	public List<StudyNoticeVO> getStudyNoticeList(String courseCode);
	
	public List<MyCourseVO> studentStudyList(MyCourseVO vo);

	public MyCourseVO studentCreditSum(MyCourseVO vo);

	public List<MyCourseVO> studentSortationCredit(MyCourseVO vo);

	public List<MyCourseVO> semesterGradeSelect(MyCourseVO vo);

	// 수강생 리스트 조회
	List<ExamScoreVO> getStudentList(ExamInfoVO vo);
	
	// 교수의 등록된 강의 년도 조회
	public List<myProfCourseVO> getProfMyCourseYear(int professorId);
	// 교수의 등록된 강의 년도 조회
	public List<myProfCourseVO> getProfMyCourseSemester(int professorId);
	
	
//	public int getTotal(MyCourseVO vo);
	

}
