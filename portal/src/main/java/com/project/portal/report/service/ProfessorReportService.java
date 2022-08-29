package com.project.portal.report.service;

import java.util.List;

import com.project.portal.course.service.CourseVO;

public interface ProfessorReportService {
	
	List<ReportVO> getReportList(CourseVO vo);
	void insertReport(ReportVO vo);
	ReportFileVO getFile(String reportFileCode);

}
