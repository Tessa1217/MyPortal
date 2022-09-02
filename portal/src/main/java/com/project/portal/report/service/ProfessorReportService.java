package com.project.portal.report.service;

import java.util.List;

import com.project.portal.course.service.CourseVO;

public interface ProfessorReportService {
	
	List<ReportVO> getReportList(CourseVO vo, ReportVO report);
	void insertReport(ReportVO vo);
	ReportFileVO getFile(String reportFileCode);
	void updateReport(ReportVO vo);
	void updateReportOnly(ReportVO vo);
	List<ReportSubmissionVO> getStudentReportList(CourseVO vo, ReportVO report);
}
