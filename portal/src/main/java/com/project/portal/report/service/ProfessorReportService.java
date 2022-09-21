package com.project.portal.report.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.portal.bachelor.service.BachelorScheduleVO;
import com.project.portal.common.Criteria;
import com.project.portal.course.service.CourseVO;

public interface ProfessorReportService {

	// 과제 관련
	// 과제 목록 조회
	List<ReportVO> getReportList(CourseVO vo, ReportVO report, Criteria cri);

	// 과제 수
	int getTotal(CourseVO course, Criteria cri);

	// 과제 등록
	void insertReport(ReportVO vo);

	// 과제 수정하기 (파일 수정을 동반)
	void updateReport(ReportVO vo);

	// 과제 수정하기 (파일 수정 없이 내용만 수정)
	void updateReportOnly(ReportVO vo);

	// 과제 삭제하기
	void deleteReport(ReportVO vo);

	// 파일 관련
	// 전체 파일 가져오기
	List<ReportFileVO> getProfWholeFile(ReportFileVO vo, Criteria cri);

	// 파일 수
	int getFileTotal(ReportFileVO vo, Criteria cri);

	// 파일 가져오기
	ReportFileVO getFile(String reportFileCode, String userCode);

	// 수강생 과제 제출 목록 조회
	List<ReportSubmissionVO> getStudentReportList(CourseVO vo, Criteria cri);

	// 과제 수
	int getReportTotal(List<ReportVO> list, Criteria cri);

	// 과제 성적 수정
	void updateScore(List<ReportSubmissionVO> subList);
	
	// 과제 이의신청 목록 조회
	public List<ReportObjectionVO> getStudentObjectionList(ReportObjectionVO vo, Criteria cri);
	// 과제 이의신청 상세 조회
	public ReportObjectionVO getStudentObjectionDetail(ReportObjectionVO vo);
	// 과제 이의신청 처리
	public void updateObjection(ReportObjectionVO vo);
	// 과제 점수 update
	public void updateObjectionScore(ReportObjectionVO vo);
	// 과제 이의신청 총 개수 조회(페이징 처리)
	public int getReportObjectionTotal(ReportObjectionVO vo , Criteria cri );
}
