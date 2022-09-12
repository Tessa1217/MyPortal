package com.project.portal.student.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.common.Criteria;
import com.project.portal.student.service.StudentMapper;
import com.project.portal.student.service.StudentService;
import com.project.portal.student.service.StudentVO;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired StudentMapper mapper;

	@Override
	public List<StudentVO> studentList(StudentVO vo) {
		return mapper.studentList(vo);
	}

	@Override
	public StudentVO studentInfoSelect(StudentVO vo) {
		return mapper.studentInfoSelect(vo);
	}

	@Override
	public StudentVO studentInfoUpdate(StudentVO vo) {
		mapper.studentInfoUpdate(vo);
		return mapper.studentInfoSelect(vo);
	}

	@Override
	public StudentVO adminStudentInfoSelect(StudentVO vo) {
		return mapper.adminStudentInfoSelect(vo);
	}

	@Override
	public StudentVO adminStudentInfoUpdate(StudentVO vo) {
		mapper.adminStudentInfoUpdate(vo);
		return mapper.adminStudentInfoSelect(vo);
	}

	@Override
	public int getTotal(StudentVO vo) {
		return mapper.getTotal(vo);
	}
}
