package com.project.portal.report.service;

import java.util.List;

import com.project.portal.course.service.CourseVO;

public interface StudentReportService {
	
	List<ReportVO> getReportList(CourseVO vo, ReportVO report);
	void insertReport(ReportSubmissionVO vo);
	ReportFileVO getFile(String reportFileCode);

}
