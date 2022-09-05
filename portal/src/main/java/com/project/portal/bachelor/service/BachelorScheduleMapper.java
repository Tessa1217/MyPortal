package com.project.portal.bachelor.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BachelorScheduleMapper {

	List<BachelorScheduleVO> scheduleList(BachelorScheduleVO vo);
	
	BachelorScheduleVO currentYearSemester(BachelorScheduleVO vo);
	
	}
