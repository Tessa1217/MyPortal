package com.project.portal.report.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.portal.course.service.CourseVO;

@Mapper
public interface ProfessorReportMapper {
	
	List<ReportVO> getReportList(CourseVO vo);
	void insertReport(ReportVO vo);
	void uploadFile(ReportFileVO vo);
	ReportFileVO getFile(@Param("reportFileCode") String reportFileCode);
	
}
