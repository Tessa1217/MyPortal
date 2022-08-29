package com.project.portal.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.portal.course.service.CourseVO;
import com.project.portal.report.service.ProfessorReportMapper;
import com.project.portal.report.service.ProfessorReportService;
import com.project.portal.report.service.ReportFileVO;
import com.project.portal.report.service.ReportVO;

@Service
public class ProfessorReportServiceImpl implements ProfessorReportService {

	@Autowired
	ProfessorReportMapper mapper;
	
	@Override
	public List<ReportVO> getReportList(CourseVO vo) {
		return mapper.getReportList(vo);
	}

	@Override
	@Transactional
	public void insertReport(ReportVO vo) {
		mapper.uploadFile(vo.getReportFile());
		vo.setReportFileCode(vo.getReportFile().getReportFileCode());
		mapper.insertReport(vo);
	}

	@Override
	public ReportFileVO getFile(String reportFileCode) {
		return mapper.getFile(reportFileCode);
	}

}
