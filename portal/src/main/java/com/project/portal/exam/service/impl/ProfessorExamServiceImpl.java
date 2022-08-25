package com.project.portal.exam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.course.service.CourseVO;
import com.project.portal.exam.service.CourseExamVO;
import com.project.portal.exam.service.ExamInfoVO;
import com.project.portal.exam.service.ExamScoreVO;
import com.project.portal.exam.service.ExamVO;
import com.project.portal.exam.service.ProfessorExamMapper;
import com.project.portal.exam.service.ProfessorExamService;

@Service
public class ProfessorExamServiceImpl implements ProfessorExamService {

	@Autowired ProfessorExamMapper mapper;
	
	@Override
	public List<ExamVO> getExamInfoList(CourseVO vo) {
		return mapper.getExamInfoList(vo);
	}
	
	@Override
	public List<ExamInfoVO> getExamList(CourseVO vo) {
		return mapper.getExamList(vo);
	}

	@Override
	public List<CourseExamVO> getCourseExam(List<ExamInfoVO> list) {
		return mapper.getCourseExam(list);
	}

	@Override
	public void insertExam(CourseVO course, ExamVO exam) {
		mapper.insertExam(course, exam);	
	}

	@Override
	public ExamVO getExam(ExamVO vo) {
		return mapper.getExam(vo);
	}

	@Override
	public List<ExamScoreVO> getExamScore(CourseVO vo) {
		return mapper.getExamScore(vo);
	}

	@Override
	public void updateExam(ExamVO vo) {
		mapper.updateExam(vo);
	}

	@Override
	public void deleteExam(ExamVO vo) {
		mapper.deleteExam(vo);
	}

}
