package com.project.portal.report.service;

import java.util.List;

import com.project.portal.common.Criteria;
import com.project.portal.course.service.CourseVO;

public interface StudentReportService {
	// 과제 리스트 조회
	List<ReportVO> getReportList(CourseVO vo, ReportVO report, Criteria cri);
	// 과제 상세 조회
	
	void insertReportSubmission(ReportSubmissionVO vo);
	public ReportVO getReportDetail(ReportVO vo);
	// 과제 제출
	public void insertReport(ReportSubmissionVO vo);
	// 파일 읽어오기
	ReportFileVO getFile(String reportFileCode);
	// 과제 수정페이지 
	public ReportVO getModDetail(ReportVO vo);
	
	
	void uploadFile(ReportFileVO vo);
	
	// 과제 제출 상세 조회
	public ReportSubmissionVO selectDetail(ReportVO vo); 
	// 이의 신청 학생 정보 조회
	public ReportObjectionVO selectStuObjection(int studentId);
	// 이의 신청 과제 정보 조회
	public ReportObjectionVO selectStuReportObjection(ReportVO vo);
	// 이의 신청 등록
	public void insertReportObjection(ReportObjectionVO vo);
	// 과제 총 개수
	public int getTotal(int studentId);


}
