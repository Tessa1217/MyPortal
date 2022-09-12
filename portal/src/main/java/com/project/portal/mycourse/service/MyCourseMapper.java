package com.project.portal.mycourse.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.portal.course.service.CourseVO;
import com.project.portal.exam.service.ExamInfoVO;
import com.project.portal.exam.service.ExamScoreVO;
import com.project.portal.exam.service.ExamVO;
import com.project.portal.lecture.service.LectureVO;
import com.project.portal.report.service.ReportVO;

@Mapper
public interface MyCourseMapper {
	
	//학생 수강 조회
	public List<MyCourseVO> getstuMyCourse(@Param("my") MyCourseVO myCourse, 
											@Param("course") CourseVO vo);
	//교수 강의 목록 조회
	public List<myProfCourseVO> getProfMyCourse(CourseVO vo);
	// 수강강의 페이지 이동
	public MyCourseMainVO getstuMyCoursePage(String courseCode);
	//교수 수강강의 페이지 이동
	public MyCourseMainVO getProfMyCoursePage(String courseCode);
	// 수강강의 계획서 조회
	public myCourseDetailVO getstuMyCourseDetail(String courseCode);
	// 수강강의 주차계획 조회
	public List<myCourseDetailVO> getstuMyCourseWeekDetail(String courseCode);
	// 강의명 조회
	public String getCourseName(String courseCode);
	// 이번주 정보
	public CourseVO getWeekCode(CourseVO vo);
	// 이주 강의 리스트
	public List<LectureVO> getLectureList(CourseVO vo);
	// 이주 과제 리스트
	public List<ReportVO> getReportList(CourseVO vo);
	// 이주 시험 조회
	public List<ExamVO> getExamList(CourseVO vo);
	
	
	public List<MyCourseVO> studentStudyList(MyCourseVO vo);

	public MyCourseVO studentCreditSum(MyCourseVO vo);
	public List<MyCourseVO> studentSortationCredit(MyCourseVO vo);
	public List<MyCourseVO> semesterGradeSelect(MyCourseVO vo);
	
	// 수강생 목록 조회하기
	List<ExamScoreVO> getStudentList(ExamInfoVO vo);
	
//	public int getTotal(MyCourseVO vo);

}
