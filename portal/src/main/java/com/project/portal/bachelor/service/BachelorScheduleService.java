package com.project.portal.bachelor.service;

import java.util.List;

public interface BachelorScheduleService {

	List<BachelorScheduleVO> scheduleList(BachelorScheduleVO vo);
	

	public String scheduleAllInsert(List<BachelorScheduleVO> list);
	
	List<BachelorScheduleVO> getMonthlyList(BachelorScheduleVO vo);
	// 개강일 기준 현재 연도 학기
	BachelorScheduleVO currentYearSemester(BachelorScheduleVO vo);
	// 성적이의신청일 기준 현재연도 학기
	BachelorScheduleVO gradeYearSemester(BachelorScheduleVO vo);
}
