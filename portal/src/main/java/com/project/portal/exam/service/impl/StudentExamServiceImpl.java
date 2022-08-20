package com.project.portal.exam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.portal.course.service.CourseVO;
import com.project.portal.exam.service.CourseExamVO;
import com.project.portal.exam.service.ExamScoreVO;
import com.project.portal.exam.service.ExamVO;
import com.project.portal.exam.service.StudentExamMapper;
import com.project.portal.exam.service.StudentExamService;
import com.project.portal.exam.service.StudentExamVO;

@Service
public class StudentExamServiceImpl implements StudentExamService {
	
	@Autowired 
	StudentExamMapper mapper;

	@Override
	public List<CourseExamVO> getStudentExam(ExamVO vo) {
		return mapper.getStudentExam(vo);
	}

	@Override
	public List<ExamScoreVO> getExamScore(List<ExamScoreVO> vo) {
		return mapper.getExamScore(vo);
	}

	@Override
	public List<ExamScoreVO> getExamInfo(int studentId, CourseVO vo) {
		return mapper.getExamInfo(studentId, vo);
	}

	@Override
	public void insertExamResult(List<StudentExamVO> vo) {
		mapper.insertExamResult(vo);
	}

	@Override
	public void updateExamScore(ExamScoreVO vo) {
		mapper.updateExamScore(vo);
	}
	
	
}
