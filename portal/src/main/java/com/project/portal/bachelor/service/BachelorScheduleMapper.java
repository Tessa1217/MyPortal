package com.project.portal.bachelor.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BachelorScheduleMapper {

	List<BachelorScheduleVO> scheduleList(BachelorScheduleVO vo);

	BachelorScheduleVO currentYearSemester(BachelorScheduleVO vo);

	int scheduleAllInsert(BachelorScheduleVO vo);

	List<BachelorScheduleVO> getMonthlyList(BachelorScheduleVO vo);

	BachelorScheduleVO gradeYearSemester(BachelorScheduleVO vo);

	// 강의계획서 날짜 불러오기
	BachelorScheduleVO yearSemester(BachelorScheduleVO vo);

	BachelorScheduleVO packageDate(BachelorScheduleVO vo);
	
	// 강의 관련 코드 받아오기
	List<BachelorGroupVO> getScheduleCode();

}
