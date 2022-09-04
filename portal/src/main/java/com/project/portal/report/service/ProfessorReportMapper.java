package com.project.portal.report.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.portal.course.service.CourseVO;
import com.project.portal.student.service.StudentVO;

@Mapper
public interface ProfessorReportMapper {
	
	// 과제 관련 
	
	// 과제 리스트 가져오기
	List<ReportVO> getReportList(@Param("course") CourseVO vo, @Param("report") ReportVO report);

	// 과제 리스트 등록
	void insertReport(ReportVO vo);

	// 과제 수정하기
	void updateReport(ReportVO vo);

	// 과제 삭제하기
	void deleteReport(ReportVO vo);
	
	// 파일 관련 
	
	// 전체 파일 가져오기 
	List<ReportFileVO> getProfWholeFile(ReportFileVO vo);
	
	// 파일 업로드
	void uploadFile(ReportFileVO vo);

	// 파일 가져오기
	ReportFileVO getFile(@Param("reportFileCode") String reportFileCode, @Param("userCode") String userCode);

	// 파일 삭제
	void deleteFile(ReportFileVO vo);

	// 수강생 과제 제출 관련 
	
	// 수강생 목록
	List<StudentVO> getStudentList(@Param("report") ReportVO vo, @Param("submission") ReportSubmissionVO submission);

	// 수강생의 과제 제출 등록하기
	void insertStudentSubmission(@Param("report") ReportVO vo, @Param("student") StudentVO student);

	// 수강생의 과제 제출 목록
	List<ReportSubmissionVO> getStudentReportList(List<ReportVO> list);

}
