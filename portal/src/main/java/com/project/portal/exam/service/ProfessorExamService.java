package com.project.portal.exam.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.portal.course.service.CourseVO;

public interface ProfessorExamService {
	
	List<ExamVO> getExamInfoList(CourseVO vo);
	List<ExamInfoVO> getExamList(CourseVO vo);
	List<CourseExamVO> getCourseExam(List<ExamInfoVO> list);
	void insertExam(@Param("course") CourseVO course, 
			@Param("exam") ExamVO exam);
	ExamVO getExam(ExamVO vo);
}
