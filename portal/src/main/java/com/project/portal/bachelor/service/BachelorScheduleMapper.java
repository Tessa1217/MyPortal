package com.project.portal.bachelor.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BachelorScheduleMapper {

	List<BachelorScheduleVO> scheduleList(BachelorScheduleVO vo);

	int scheduleAllInsert(BachelorScheduleVO vo);

	List<BachelorScheduleVO> getMonthlyList(BachelorScheduleVO vo);
	
	// 계획 조회하는 조회 쿼리
	BachelorScheduleVO getScheduleInfo(BachelorScheduleVO vo);
	
	// 현학기 계획 날짜 조회
	BachelorScheduleVO getCurrentScheduleInfo(BachelorScheduleVO vo);
	
	// 강의 관련 코드 받아오기
	List<BachelorGroupVO> getScheduleCode();

	BachelorScheduleVO getYearSemester(BachelorScheduleVO vo);
	
	void scheduleInsert(BachelorScheduleVO vo);
}
