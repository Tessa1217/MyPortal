package com.project.portal.exam.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.portal.course.service.CourseVO;

@Mapper
public interface ProfessorExamMapper {
	
	// 시험 리스트 
	List<ExamVO> getExamInfoList(CourseVO vo);
	List<ExamInfoVO> getExamList(ExamInfoVO vo);
	// 강의 시험지 리스트
	List<CourseExamVO> getCourseExam(ExamInfoVO vo);
	// 시험 정보 등록
	void insertExam(@Param("course") CourseVO course, 
			@Param("exam") ExamVO exam);
	ExamVO getExam(ExamVO vo);
	// 시험 정보 변경
	void updateExam(ExamVO vo);
	// 시험 정보 삭제 
	void deleteExam(ExamVO vo);
	// 시험 성적 리스트
	List<ExamScoreVO> getExamScore(CourseVO vo);
	// 새로운 문제 등록 (Question - 문제, QuestionOption - 문제의 문항)
	void insertQuestion(QuestionVO vo);
	void insertQuestionOption(QuestionOptionVO vo);
	// 시험지 등록
	void insertCourseExam(CourseExamVO vo);
}
