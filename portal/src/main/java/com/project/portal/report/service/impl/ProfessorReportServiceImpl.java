package com.project.portal.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.portal.common.Criteria;
import com.project.portal.course.service.CourseVO;
import com.project.portal.report.service.ProfessorReportMapper;
import com.project.portal.report.service.ProfessorReportService;
import com.project.portal.report.service.ReportFileVO;
import com.project.portal.report.service.ReportObjectionVO;
import com.project.portal.report.service.ReportSubmissionVO;
import com.project.portal.report.service.ReportVO;
import com.project.portal.student.service.StudentVO;

@Service
public class ProfessorReportServiceImpl implements ProfessorReportService {

	@Autowired
	ProfessorReportMapper mapper;
	
	@Override
	public List<ReportVO> getReportList(CourseVO vo, ReportVO report, Criteria cri) {
		return mapper.getReportList(vo, report, cri);
	}

	@Override
	@Transactional
	public void insertReport(ReportVO vo) {
		mapper.uploadFile(vo.getReportFile());
		vo.setReportFileCode(vo.getReportFile().getReportFileCode());
		mapper.insertReport(vo);
		List<StudentVO> studentList = mapper.getStudentList(vo, null);
		for (StudentVO student : studentList) {
			mapper.insertStudentSubmission(vo, student);
		}
	}

	@Override
	public ReportFileVO getFile(String reportFileCode, String userCode) {
		return mapper.getFile(reportFileCode, userCode);
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

	@Override
	public List<ReportSubmissionVO> getStudentReportList(CourseVO vo, Criteria cri) {
		ReportVO report = null;
		if (cri.getKeyword() != null || cri.getKeyword() != "") {
			report = new ReportVO();
			report.setReportCode(cri.getKeyword());
		}
		List<ReportVO> list = mapper.getReportList(vo, report, null);
		List<ReportSubmissionVO> subList = mapper.getStudentReportList(list, cri);
		report.setCourseCode(vo.getCourseCode());
		for (ReportSubmissionVO sub : subList) {
			List<StudentVO> student = mapper.getStudentList(report, sub);
			sub.setStudentInfo(student.get(0));
		}
		return subList;
	}

	@Override
	public void deleteReport(ReportVO vo) {
		ReportFileVO file = new ReportFileVO();
		file.setReportFileCode(vo.getReportFileCode());
		mapper.deleteReport(vo);
		mapper.deleteFile(file);
	}

	@Override
	public List<ReportFileVO> getProfWholeFile(ReportFileVO vo, Criteria cri) {
		return mapper.getProfWholeFile(vo, cri);
	}

	@Override
	public int getTotal(CourseVO course, Criteria cri) {
		return mapper.getTotal(course, cri);
	}

	@Override
	public int getReportTotal(List<ReportVO> list, Criteria cri) {
		return mapper.getReportTotal(list, cri);
	}

	@Override
	public void updateScore(List<ReportSubmissionVO> subList) {
		for (ReportSubmissionVO sub : subList) {
			mapper.updateScore(sub);
		}
	}

	@Override
	public int getFileTotal(ReportFileVO vo, Criteria cri) {
		return mapper.getFileTotal(vo, cri);
	}

	@Override
	public List<ReportObjectionVO> getStudentObjectionList(ReportObjectionVO vo, Criteria cri) {
		return mapper.getStudentObjectionList(vo, cri);
	}

	@Override
	public ReportObjectionVO getStudentObjectionDetail(ReportObjectionVO vo) {
		return mapper.getStudentObjectionDetail(vo);
	}

	@Override
	public void updateObjection(ReportObjectionVO vo) {
		mapper.updateObjection(vo);
	}

	@Override
	public void updateObjectionScore(ReportObjectionVO vo) {
		mapper.updateObjectionScore(vo);
		
	}
	
	@Override
	public int getReportObjectionTotal(ReportObjectionVO vo , Criteria cri ) {
		// TODO Auto-generated method stub
		return mapper.getReportObjectionTotal(vo, cri);
	}

}
