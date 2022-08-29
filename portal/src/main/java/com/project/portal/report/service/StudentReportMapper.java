package com.project.portal.report.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.portal.course.service.CourseVO;

@Mapper
public interface StudentReportMapper {
	
	List<ReportVO> getReportList(@Param("courseInfo") CourseVO vo,
								@Param("report") ReportVO report);
	void insertReportSubmission(ReportSubmissionVO vo);
	void uploadFile(ReportFileVO vo);
	ReportFileVO getFile(@Param("reportFileCode") String reportFileCode);

}
