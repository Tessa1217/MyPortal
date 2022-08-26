package com.project.portal.exam.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.portal.course.service.CourseVO;

public interface ProfessorExamService {
	
	List<ExamVO> getExamInfoList(CourseVO vo);
	List<ExamInfoVO> getExamList(ExamInfoVO vo);
	List<CourseExamVO> getCourseExam(ExamInfoVO vo);
	void insertExam(CourseVO course, ExamVO exam);
	void deleteExam(ExamVO vo);
	ExamVO getExam(ExamVO vo);
	void updateExam(ExamVO vo);
	List<ExamScoreVO> getExamScore(CourseVO vo);
	void createQuestion(QuestionVO vo);
	void insertCourseExam(List<CourseExamVO> list);
}
