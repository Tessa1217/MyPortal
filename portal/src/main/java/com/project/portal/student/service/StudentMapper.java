package com.project.portal.student.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.portal.common.Criteria;

@Mapper
public interface StudentMapper {
	
	public List<StudentVO> studentList(StudentVO vo);

	public StudentVO studentInfoSelect(StudentVO vo);
	
	public int studentInfoUpdate(StudentVO vo);

	public StudentVO adminStudentInfoSelect(StudentVO vo);

	public int adminStudentInfoUpdate(StudentVO vo);

	int getTotal(StudentVO vo);

}
