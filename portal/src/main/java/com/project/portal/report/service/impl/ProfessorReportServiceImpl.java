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
import com.project.portal.student.service.StudentVO;

@Service
public class ProfessorReportServiceImpl implements ProfessorReportService {

	@Autowired
	ProfessorReportMapper mapper;
	
	@Override
	public List<ReportVO> getReportList(CourseVO vo, ReportVO report) {
		return mapper.getReportList(vo, report);
	}

	@Override
	@Transactional
	public void insertReport(ReportVO vo) {
		mapper.uploadFile(vo.getReportFile());
		vo.setReportFileCode(vo.getReportFile().getReportFileCode());
		mapper.insertReport(vo);
		System.out.println(vo);
		List<StudentVO> studentList = mapper.getStudentList(vo);
		for (StudentVO student : studentList) {
			mapper.insertStudentSubmission(vo, student);
		}
	}

	@Override
	public ReportFileVO getFile(String reportFileCode) {
		return mapper.getFile(reportFileCode);
	}

	@Override
	@Transactional
	public void updateReport(ReportVO vo) {
		ReportFileVO deleteFile = new ReportFileVO();
		deleteFile.setReportFileCode(vo.getReportFileCode());
		mapper.deleteFile(deleteFile);
		mapper.uploadFile(vo.getReportFile());
		vo.setReportFileCode(vo.getReportFile().getReportFileCode());
		mapper.updateReport(vo);
	}

	@Override
	public void updateReportOnly(ReportVO vo) {
		mapper.updateReport(vo);
	}

}
