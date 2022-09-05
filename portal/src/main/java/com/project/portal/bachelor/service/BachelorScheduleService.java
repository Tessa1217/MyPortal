package com.project.portal.bachelor.service;

import java.util.List;

public interface BachelorScheduleService {

	List<BachelorScheduleVO> scheduleList(BachelorScheduleVO vo);
	
	BachelorScheduleVO currentYearSemester(BachelorScheduleVO vo);
}
