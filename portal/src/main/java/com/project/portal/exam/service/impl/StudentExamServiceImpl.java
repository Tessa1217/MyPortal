package com.project.portal.exam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.course.service.CourseVO;
import com.project.portal.exam.service.CourseExamVO;
import com.project.portal.exam.service.ExamVO;
import com.project.portal.exam.service.StudentExamMapper;
import com.project.portal.exam.service.StudentExamService;

@Service
public class StudentExamServiceImpl implements StudentExamService {
	
	@Autowired 
	StudentExamMapper mapper;

	@Override
	public List<ExamVO> getExamInfo(CourseVO vo) {
		return mapper.getExamInfo(vo);
	}

	@Override
	public List<CourseExamVO> getStudentExam(ExamVO vo) {
		return mapper.getStudentExam(vo);
	}
	
	

}
