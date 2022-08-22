package com.project.portal.exam.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.portal.course.service.CourseVO;

public interface StudentExamService {
	
	List<ExamScoreVO> getExamInfo(int studentId, CourseVO vo);
	List<CourseExamVO> getStudentExam(ExamVO vo);
	List<ExamScoreVO> getExamScore(List<ExamScoreVO> vo);
	List<ExamResultVO> getExamResult(int studentId, ExamVO vo);
	void insertExamResult(ExamTakeVO vo);	
}
