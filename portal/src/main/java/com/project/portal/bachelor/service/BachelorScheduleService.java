package com.project.portal.bachelor.service;

import java.util.List;

public interface BachelorScheduleService {
	
	BachelorScheduleVO getScheduleInfo(BachelorScheduleVO vo);
	// 현학기 계획 날짜 조회
	BachelorScheduleVO getCurrentScheduleInfo(BachelorScheduleVO vo);
	
	List<BachelorScheduleVO> scheduleList(BachelorScheduleVO vo);
	

	public String scheduleAllInsert(List<BachelorScheduleVO> list);
	
	List<BachelorScheduleVO> getMonthlyList(BachelorScheduleVO vo);
	
	List<BachelorGroupVO> getScheduleCode();

	// 시작일 기준 연도 학기 구 구하기
	BachelorScheduleVO getYearSemester(BachelorScheduleVO vo);
	
	void scheduleInsert(BachelorScheduleVO vo);
}
