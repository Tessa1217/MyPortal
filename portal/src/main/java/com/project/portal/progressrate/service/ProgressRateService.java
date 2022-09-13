package com.project.portal.progressrate.service;

import java.util.List;

public interface ProgressRateService {
	public ProgressRateVO selectWeekAtt();
	//출석 진행 정보 조회
	public List<ProgressRateVO> selectAttProgress(ProgressRateVO vo);
	//강의 진행 정보 조회
	public List<ProgressRateVO> selectLectureProgress(ProgressRateVO vo);
	//과제 진행 정보 조회
	public List<ProgressRateVO> selectReportProgress(ProgressRateVO vo);
	public List<ProgressRateVO> selectReportProgressRate(ProgressRateVO vo);
	//중간시험 진행 정보 조회
	public ProgressRateVO selectMidExamProgress(ProgressRateVO vo);
	//기말시험 진행 정보 조회
	public ProgressRateVO selectFinalExamProgress(ProgressRateVO vo);
	
	
	
}