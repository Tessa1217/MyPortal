package com.project.portal.exam.service;

import java.util.List;

import com.project.portal.course.service.CourseVO;

public interface ProfessorExamService {
	
	List<ExamVO> getExamList(CourseVO vo, ExamVO exam);
	List<ExamInfoVO> getExamInfoList(ExamInfoVO vo);
	List<CourseExamVO> getCourseExam(ExamInfoVO vo, List<CourseExamVO> list);
	void insertExam(CourseVO course, ExamVO exam);
	void deleteExam(ExamVO vo);
	void updateExam(ExamVO vo);
	List<ExamScoreVO> getExamScore(CourseVO vo);
	void createQuestion(QuestionVO vo);
	void insertCourseExam(List<CourseExamVO> list);
	void finalExamSubmit(ExamInfoVO vo);
}
