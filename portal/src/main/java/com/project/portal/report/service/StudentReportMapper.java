package com.project.portal.report.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.portal.course.service.CourseVO;

@Mapper
public interface StudentReportMapper {
	
	
	List<ReportVO> getReportList(@Param("courseInfo") CourseVO vo, @Param("report") ReportVO report);
	 
	List<ReportVO> getReportList(CourseVO vo);
	void insertReportSubmission(ReportSubmissionVO vo);
	// 파일 업로드
	void uploadFile(ReportFileVO vo);
	// 과제 상세 조회
	public ReportVO getReportDetail(String reportCode);
	// 과제 제출
	public void insertReport(ReportSubmissionVO vo);
	// 파일 읽어오기
	ReportFileVO getFile(String reportFileCode);
	// 과제 제출 상세 조회
	public ReportSubmissionVO selectDetail(ReportVO vo); 
	// 이의 신청 학생 정보 조회
	public ReportObjectionVO selectStuObjection(int studentId);
	// 이의 신청 과제 정보 조회
	public ReportObjectionVO selectStuReportObjection(ReportVO vo);
	// 이의 신청 등록
	public void insertReportObjection(ReportObjectionVO vo);
	
}
