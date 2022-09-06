package com.project.portal.bachelor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.bachelor.service.BachelorScheduleMapper;
import com.project.portal.bachelor.service.BachelorScheduleService;
import com.project.portal.bachelor.service.BachelorScheduleVO;

@Service
public class BachelorScheduleServiceImpl implements BachelorScheduleService{
	
	@Autowired BachelorScheduleMapper mapper;

	@Override
	public List<BachelorScheduleVO> scheduleList(BachelorScheduleVO vo) {
		return mapper.scheduleList(vo);
	}

	@Override
	public BachelorScheduleVO currentYearSemester(BachelorScheduleVO vo) {
		return mapper.currentYearSemester(vo);
	}

	@Override
	public String scheduleAllInsert(List<BachelorScheduleVO> list) {
		for(BachelorScheduleVO vo : list) {
			mapper.scheduleAllInsert(vo);
		}
		return "";
	}

}
