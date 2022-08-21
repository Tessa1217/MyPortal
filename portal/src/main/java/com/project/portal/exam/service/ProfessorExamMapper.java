package com.project.portal.exam.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.portal.course.service.CourseVO;

@Mapper
public interface ProfessorExamMapper {
	
	List<ExamVO> getExamInfoList(CourseVO vo);
	List<ExamInfoVO> getExamList(CourseVO vo);
	List<CourseExamVO> getCourseExam(List<ExamInfoVO> list);

}
