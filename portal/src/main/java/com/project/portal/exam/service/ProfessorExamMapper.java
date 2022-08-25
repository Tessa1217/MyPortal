package com.project.portal.exam.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.portal.course.service.CourseVO;

@Mapper
public interface ProfessorExamMapper {
	
	List<ExamVO> getExamInfoList(CourseVO vo);
	List<ExamInfoVO> getExamList(CourseVO vo);
	List<CourseExamVO> getCourseExam(List<ExamInfoVO> list);
	void insertExam(@Param("course") CourseVO course, 
			@Param("exam") ExamVO exam);
	void deleteExam(ExamVO vo);
	ExamVO getExam(ExamVO vo);
	void updateExam(ExamVO vo);
	List<ExamScoreVO> getExamScore(CourseVO vo);

}
