package com.project.portal.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.portal.bachelor.service.BachelorScheduleVO;
import com.project.portal.common.service.ScheduleMapper;
import com.project.portal.common.service.ScheduleService;

@Component
public class ScheduleServiceImpl implements ScheduleService {
	
	@Autowired
	ScheduleMapper mapper;
	
	@Override
	public void updateExamScore(BachelorScheduleVO vo) {
		mapper.updateExamScore(vo);
	}

	@Override
	public void updateLectureScore(BachelorScheduleVO vo) {
		mapper.updateLectureScore(vo);
		
	}

	@Override
	public void updateReportScore(BachelorScheduleVO vo) {
		mapper.updateReportScore(vo);
	}

	@Override
	public void manageFile(BachelorScheduleVO vo) {
		mapper.manageFile(vo);
	}

}
