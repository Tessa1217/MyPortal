package com.project.portal.common.web;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleController {
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void autoReportScore() {
		
	}
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void autoExamScore() {
		
	}
}
