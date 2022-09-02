package com.project.portal.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.course.service.CourseVO;
import com.project.portal.report.service.ReportFileVO;
import com.project.portal.report.service.ReportObjectionVO;
import com.project.portal.report.service.ReportSubmissionVO;
import com.project.portal.report.service.ReportVO;
import com.project.portal.report.service.StudentReportMapper;
import com.project.portal.report.service.StudentReportService;

@Service
public class StudentReportServiceImpl implements StudentReportService {

	@Autowired
	StudentReportMapper mapper;
	@Override
	public List<ReportVO> getReportList(CourseVO vo, ReportVO report) {
		return mapper.getReportList(vo, report);
	}

	@Override
	public void insertReport(ReportSubmissionVO vo) {
		mapper.insertReportSubmission(vo);
	}

	@Override
	public ReportFileVO getFile(String reportFileCode) {
		return mapper.getFile(reportFileCode);
	}

	@Override
	public ReportVO getReportDetail(String reportCode) {
		// TODO Auto-generated method stub
		return mapper.getReportDetail(reportCode);
	}

	@Override
	public void insertReportSubmission(ReportSubmissionVO vo) {
		// TODO Auto-generated method stub
		mapper.insertReportSubmission(vo);
	}

	@Override
	public void uploadFile(ReportFileVO vo) {
		// TODO Auto-generated method stub
		mapper.uploadFile(vo);
	}

	@Override
	public ReportSubmissionVO selectDetail(ReportVO vo) {
		// TODO Auto-generated method stub
		return mapper.selectDetail(vo);
	}

	@Override
	public ReportObjectionVO selectStuObjection(int studentId) {
		// TODO Auto-generated method stub
		return mapper.selectStuObjection(studentId);
	}

	@Override
	public ReportObjectionVO selectStuReportObjection(ReportVO vo) {
		// TODO Auto-generated method stub
		return mapper.selectStuReportObjection(vo);
	}

	@Override
	public void insertReportObjection(ReportObjectionVO vo) {
		// TODO Auto-generated method stub
		mapper.insertReportObjection(vo);
	}


}
