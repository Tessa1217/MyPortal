package com.project.portal.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.project.portal.bachelor.service.BachelorScheduleVO;
import com.project.portal.bachelor.service.impl.BachelorScheduleServiceImpl;
import com.project.portal.common.service.impl.ScheduleServiceImpl;

// 작성자: 권유진
@Component
public class ScheduleController {
	
	@Autowired
	BachelorScheduleServiceImpl bservice;
	@Autowired
	ScheduleServiceImpl service;
	
	private BachelorScheduleVO schedule = new BachelorScheduleVO();

	
	@Scheduled(cron = "0 0 0 * * ?")
	public void autoExamScore() {
		schedule.setDetailCode("BPLAN00");
		schedule = bservice.getScheduleInfo(schedule);
		service.updateExamScore(schedule);
	}
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void autoLectureScore() {
		schedule.setDetailCode("BPLAN00");
		schedule = bservice.getScheduleInfo(schedule);
		service.updateLectureScore(schedule);
	}
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void autoUpdateScore() {
		schedule.setDetailCode("BPLAN00");
		schedule = bservice.getScheduleInfo(schedule);
		service.updateReportScore(schedule);
	}
	
	@Scheduled(cron = "0 0 0 1 1 ?")
	public void manageFile() {
		schedule.setDetailCode("BPLAN00");
		schedule = bservice.getScheduleInfo(schedule);
		service.manageFile(schedule);
	}
}
