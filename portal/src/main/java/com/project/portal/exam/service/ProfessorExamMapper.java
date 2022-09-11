package com.project.portal.exam.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.portal.common.Criteria;
import com.project.portal.course.service.CourseVO;
import com.project.portal.course.service.CourseWeeklyVO;

@Mapper
public interface ProfessorExamMapper {
	
	// 시험 일자
	CourseWeeklyVO getTestDate(@Param("course") CourseVO course, @Param("detailCode") String detailCode);

	// 시험 리스트 (전체 시험 리스트 또는 특정 조건에 해당하는 시험)
	List<ExamVO> getExamList(@Param("course") CourseVO vo, @Param("exam") ExamVO exam);

	// 이전 시험에 관한 정보 리스트
	List<ExamInfoVO> getExamInfoList(ExamInfoVO vo);

	// 강의 시험지 리스트 (이미 있는 문제들에 대한 필터 옵션까지 포함)
	List<CourseExamVO> getCourseExam(@Param("exam") ExamInfoVO vo, @Param("filters") List<CourseExamVO> list);

	// 시험 정보 등록
	void insertExam(@Param("course") CourseVO course, @Param("exam") ExamVO exam);

	// 시험 정보 변경
	void updateExam(ExamVO vo);

	// 시험 정보 삭제
	void deleteExam(ExamVO vo);

	// 시험 성적 리스트
	List<ExamScoreVO> getExamScore(@Param("course") CourseVO vo, 
									@Param("cri") Criteria cri);
	
	// 페이징 처리 위한 총 Row 수
	int getExamScoreTotal(@Param("course") CourseVO vo, 
							@Param("cri") Criteria cri);

	// 새로운 문제 등록 (Question - 문제, QuestionOption - 문제의 문항)
	void insertQuestion(QuestionVO vo);
	
	void insertQuestionOption(QuestionOptionVO vo);

	// 문제 삭제
	void deleteQuestion(QuestionVO vo);

	// 문제 수정
	void updateQuestion(QuestionVO vo);

	// 시험지 등록 (시험 정보와 문제 정보를 연결하는 실제 시험지 정보 등록)
	void insertCourseExam(CourseExamVO vo);

	// 시험지 최종 등록 시 최종 등록 상태로 변경
	void updateExamStatus(ExamInfoVO vo);
	
	// 시험지 최종 등록 시 문제 등록 상태로 변경
	void updateCourseExamCompletion(ExamInfoVO vo);

	// 시험지 등록 시 수강생 별로 시험에 관한 정보 등록
	void insertExamScore(ExamScoreVO vo);

}
