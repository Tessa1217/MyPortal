package com.project.portal.report.service;

import java.util.List;

import com.project.portal.course.service.CourseVO;

public interface StudentReportService {
	// 과제 리스트 조회
	List<ReportVO> getReportList(CourseVO vo, ReportVO report);
	// 과제 상세 조회
	
	void insertReportSubmission(ReportSubmissionVO vo);
	public ReportVO getReportDetail(String reportCode);
	// 과제 제출
	public void insertReport(ReportSubmissionVO vo);
	// 파일 읽어오기
	ReportFileVO getFile(String reportFileCode);
	
	
	
	void uploadFile(ReportFileVO vo);
	
	// 과제 제출 상세 조회
	public ReportSubmissionVO selectDetail(ReportVO vo); 
	// 이의 신청 학생 정보 조회
	public ReportObjectionVO selectStuObjection(int studentId);
	// 이의 신청 과제 정보 조회
	public ReportObjectionVO selectStuReportObjection(ReportVO vo);
	// 이의 신청 등록
	public void insertReportObjection(ReportObjectionVO vo);


}
