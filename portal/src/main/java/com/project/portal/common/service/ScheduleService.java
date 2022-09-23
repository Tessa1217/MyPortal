package com.project.portal.common.service;

import com.project.portal.bachelor.service.BachelorScheduleVO;

public interface ScheduleService {
	
	void updateExamScore(BachelorScheduleVO vo);
	void updateLectureScore(BachelorScheduleVO vo);
	void updateReportScore(BachelorScheduleVO vo);
	void manageFile(BachelorScheduleVO vo);

}
