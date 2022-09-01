package com.project.portal.report.service;

import java.util.List;

import com.project.portal.course.service.CourseVO;

public interface StudentReportService {
	// 과제 리스트 조회
	List<ReportVO> getReportList(CourseVO vo, ReportVO report);
	// 과제 상세 조회
	public ReportVO getReportDetail(String reportCode);
	//
	void insertReport(ReportSubmissionVO vo);
	//
	ReportFileVO getFile(String reportFileCode);

}
