package com.project.portal.student.service;

import java.util.List;

import com.project.portal.common.Criteria;

public interface StudentService {

	public List<StudentVO> studentList(Criteria cri);

	public StudentVO studentInfoSelect(StudentVO vo);

	public StudentVO studentInfoUpdate(StudentVO vo);

	public StudentVO adminStudentInfoSelect(StudentVO vo);

}
