package com.project.portal.exam.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.portal.course.service.CourseVO;

public interface StudentExamService {
	
	List<ExamScoreVO> getExamInfo(@Param("studentId") int studentId, @Param("course")CourseVO vo);
	List<CourseExamVO> getStudentExam(ExamVO vo);
	List<ExamScoreVO> getExamScore(List<ExamScoreVO> vo);

}
