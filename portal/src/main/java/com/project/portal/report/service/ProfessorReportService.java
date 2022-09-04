package com.project.portal.report.service;

import java.util.List;

import com.project.portal.course.service.CourseVO;

public interface ProfessorReportService {
	
	// 과제 관련 
	// 과제 목록 조회
	List<ReportVO> getReportList(CourseVO vo, ReportVO report);
	// 과제 등록
	void insertReport(ReportVO vo);
	// 과제 수정하기 (파일 수정을 동반)
	void updateReport(ReportVO vo);
	// 과제 수정하기 (파일 수정 없이 내용만 수정)
	void updateReportOnly(ReportVO vo);
	// 과제 삭제하기
	void deleteReport(ReportVO vo);
	
	// 파일 관련
	
	// 전체 파일 가져오기
	List<ReportFileVO> getProfWholeFile(ReportFileVO vo);
	// 파일 가져오기
	ReportFileVO getFile(String reportFileCode, String userCode);
	
	// 수강생 과제 제출 목록 조회
	List<ReportSubmissionVO> getStudentReportList(CourseVO vo, ReportVO report);
}
