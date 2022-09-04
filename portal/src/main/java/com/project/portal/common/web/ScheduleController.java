package com.project.portal.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.project.portal.common.service.impl.ScheduleServiceImpl;
import com.project.portal.course.service.CourseVO;
import com.project.portal.course.service.impl.CourseServiceImpl;

// 작성자: 권유진
@Service
public class ScheduleController {
	
	@Autowired
	CourseServiceImpl cService;
	@Autowired
	ScheduleServiceImpl service;
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void autoReportScore(CourseVO vo) {
		vo = cService.getCurrent();
		service.updateExamScore(vo);
	}
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void autoExamScore() {
		
	}
}
