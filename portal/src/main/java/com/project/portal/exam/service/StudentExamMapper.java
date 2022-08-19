package com.project.portal.exam.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.portal.course.service.CourseVO;

@Mapper
public interface StudentExamMapper {
	
	List<ExamVO> getExamInfo(CourseVO vo);
	List<CourseExamVO> getStudentExam(ExamVO vo);

}
