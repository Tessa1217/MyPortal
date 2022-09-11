package com.project.portal.exam.service;

import java.util.List;
import java.util.Map;

import com.project.portal.common.Criteria;
import com.project.portal.course.service.CourseVO;
import com.project.portal.course.service.CourseWeeklyVO;

public interface ProfessorExamService {
	
	// 시험 일자 정보
	Map<String, CourseWeeklyVO> getTestDate(CourseVO vo, String detailCode1, String detailCode2);

	// 현재 강의 시험지 목록
	List<ExamVO> getExamList(CourseVO vo, ExamVO exam);

	// 교수의 예전 시험 정보
	List<ExamInfoVO> getExamInfoList(ExamInfoVO vo);

	// 시험의 시험지 정보
	List<CourseExamVO> getCourseExam(ExamInfoVO vo, List<CourseExamVO> list);

	// 시험 등록
	void insertExam(CourseVO course, ExamVO exam);

	// 시험 삭제
	void deleteExam(ExamVO vo);

	// 시험 변경
	void updateExam(ExamVO vo);

	// 수강생 시험 점수
	List<ExamScoreVO> getExamScore(CourseVO vo, Criteria cri);

	// 페이징 처리 위한 총 Row 수
	int getExamScoreTotal(CourseVO vo, Criteria cri);

	// 문제 생성
	void createQuestion(QuestionVO vo);
	
	// 문제 삭제 
	void deleteQuestion(QuestionVO vo);
	
	// 문제 변경 
	void updateQuestion(QuestionVO vo);

	// 시험지 임시 저장 또는 저장
	void insertCourseExam(List<CourseExamVO> list);

	// 시험지 최종 제출
	void finalExamSubmit(ExamInfoVO vo, List<ExamScoreVO> studentList);

}
