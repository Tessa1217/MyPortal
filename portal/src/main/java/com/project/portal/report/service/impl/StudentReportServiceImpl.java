package com.project.portal.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.common.Criteria;
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
	public List<ReportVO> getReportList(CourseVO vo, ReportVO report , Criteria cri) {
		return mapper.getReportList(vo, report ,cri);
	}

	@Override
	public void insertReport(ReportSubmissionVO vo) {
		mapper.insertReportSubmission(vo);
	}

	@Override
	public String getFile(ReportFileVO vo) {
		return mapper.getFile(vo);
	}

	@Override
	public ReportVO getReportDetail(ReportVO vo) {
		// TODO Auto-generated method stub
		return mapper.getReportDetail(vo);
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

	@Override
	public ReportVO getModDetail(ReportVO vo) {
		// TODO Auto-generated method stub
		return mapper.getModDetail(vo);
	}

	@Override
	public int getTotal(ReportVO vo) {
		// TODO Auto-generated method stub
		return mapper.getTotal(vo);
	}

	@Override
	public void reportModify(String reportFileCode) {
		// TODO Auto-generated method stub
		mapper.reportModify(reportFileCode);
	}

	@Override
	public void deleteReportFile(ReportFileVO vo) {
		// TODO Auto-generated method stub
		mapper.deleteReportFile(vo);
	}

	@Override
	public void reportFileCodeUpdate(ReportSubmissionVO vo) {
		// TODO Auto-generated method stub
		mapper.reportFileCodeUpdate(vo);
	}

	@Override
	public ReportVO getProfFileInfo(ReportVO vo) {
		// TODO Auto-generated method stub
		return mapper.getProfFileInfo(vo);
	}

	@Override
	public ReportObjectionVO getReportObjectionDetail(String subCode) {
		// TODO Auto-generated method stub
		return mapper.getReportObjectionDetail(subCode);
	}



}
