package com.project.portal.bachelor.service;

import java.util.List;

public interface BachelorScheduleService {

	List<BachelorScheduleVO> scheduleList(BachelorScheduleVO vo);
	
	BachelorScheduleVO currentYearSemester(BachelorScheduleVO vo);

	public String scheduleAllInsert(List<BachelorScheduleVO> list);
}
