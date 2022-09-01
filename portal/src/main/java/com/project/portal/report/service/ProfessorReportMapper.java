package com.project.portal.report.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.portal.course.service.CourseVO;

@Mapper
public interface ProfessorReportMapper {
	
	List<ReportVO> getReportList(@Param("course") CourseVO vo, @Param("report") ReportVO report);
	void insertReport(ReportVO vo);
	void uploadFile(ReportFileVO vo);
	ReportFileVO getFile(@Param("reportFileCode") String reportFileCode);
	void deleteFile(ReportFileVO vo);
	void updateReport(ReportVO vo);
	
}
