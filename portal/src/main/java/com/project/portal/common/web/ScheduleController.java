package com.project.portal.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.project.portal.exam.service.ProfessorExamService;
import com.project.portal.exam.service.StudentExamService;
import com.project.portal.report.service.ProfessorReportService;
import com.project.portal.report.service.StudentReportService;

// 작성자: 권유진
@Service
public class ScheduleController {
	
	@Autowired
	ProfessorReportService prService;
	@Autowired
	StudentReportService srService;
	@Autowired
	ProfessorExamService peService;
	@Autowired
	StudentExamService seService;
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void autoReportScore() {
		
	}
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void autoExamScore() {
		
	}
}
