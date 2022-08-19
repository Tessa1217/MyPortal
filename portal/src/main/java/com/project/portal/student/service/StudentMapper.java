package com.project.portal.student.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.portal.common.Criteria;

@Mapper
public interface StudentMapper {
	
	public List<StudentVO> studentList(Criteria cri);

	public StudentVO studentInfoSelect(StudentVO vo);

}
