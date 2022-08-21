package com.project.portal.exam.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.portal.course.service.CourseVO;

@Mapper
public interface StudentExamMapper {
	
	List<ExamScoreVO> getExamInfo(@Param("studentId") int studentId, @Param("course")CourseVO vo);
	List<CourseExamVO> getStudentExam(ExamVO vo);
	List<ExamScoreVO> getExamScore(List<ExamScoreVO> vo);
	List<ExamResultVO> getExamResult(@Param("studentId") int studentId, @Param("exam")ExamVO vo);
	void insertExamResult(List<StudentExamVO> vo);
	void updateExamScore(ExamScoreVO vo);

}
