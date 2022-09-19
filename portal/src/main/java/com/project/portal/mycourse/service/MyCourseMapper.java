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
import com.project.portal.studynotice.service.StudyNoticeVO;

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
	// 최근 강의 공지사항 조회
	public List<StudyNoticeVO> getStudyNoticeList(String courseCode);
	
	// 학생 학업 정보 조회
	public List<MyCourseVO> studentStudyList(MyCourseVO vo);
	// 학점 총계 조회
	public MyCourseVO studentCreditSum(MyCourseVO vo);
	// 이수구분별 학점 조회
	public List<MyCourseVO> studentSortationCredit(MyCourseVO vo);
	// 학생 학기별 성적 조회
	public List<MyCourseVO> semesterGradeSelect(MyCourseVO vo);
	
	// 수강생 목록 조회하기
	List<ExamScoreVO> getStudentList(ExamInfoVO vo);
	// 교수의 등록된 강의 년도 조회
	public List<myProfCourseVO> getProfMyCourseYear(int professorId);
	// 교수의 등록된 강의 년도 조회
	public List<myProfCourseVO> getProfMyCourseSemester(int professorId);
	// 학생의 등록된 강의 년도 조회
	public List<MyCourseVO> getStuCourseYear(int studentId);
	// 학생의 등록된 강의 학기 조회
	public List<MyCourseVO> getStuCourseSemester(int studentId);
	public int getTotal(MyCourseVO vo);

}
