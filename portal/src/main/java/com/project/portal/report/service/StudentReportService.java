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
	// 과제 등록 파일 경로 가져오기
	public String getFile(ReportFileVO vo);
	// 과제 수정페이지 
	public ReportVO getModDetail(ReportVO vo);
	// 과제 수정 처리
	public void reportModify(String reportFileCode);
	// 과제 파일 삭제
	public void deleteReportFile(ReportFileVO vo);
	// 파일 코드 업데이트
	public void reportFileCodeUpdate(ReportSubmissionVO vo);
	// 교수가 제출한 파일 정보 가져오기
	public ReportVO getProfFileInfo(ReportVO vo);
	// 학생 이의 신청결과 상세조회
	public ReportObjectionVO getReportObjectionDetail(String subCode);

	
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
	public int getTotal(ReportVO vo);


}
