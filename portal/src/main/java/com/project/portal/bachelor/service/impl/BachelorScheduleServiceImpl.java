package com.project.portal.bachelor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.bachelor.service.BachelorGroupVO;
import com.project.portal.bachelor.service.BachelorScheduleMapper;
import com.project.portal.bachelor.service.BachelorScheduleService;
import com.project.portal.bachelor.service.BachelorScheduleVO;

@Service
public class BachelorScheduleServiceImpl implements BachelorScheduleService{
	
	@Autowired BachelorScheduleMapper mapper;
	// 학사일정 리스트
	@Override
	public List<BachelorScheduleVO> scheduleList(BachelorScheduleVO vo) {
		return mapper.scheduleList(vo);
	}

	@Override
	public String scheduleAllInsert(List<BachelorScheduleVO> list) {
		for(BachelorScheduleVO vo : list) {
			mapper.scheduleAllInsert(vo);
		}
		return "";
	}

	@Override
	public List<BachelorScheduleVO> getMonthlyList(BachelorScheduleVO vo) {
		return mapper.getMonthlyList(vo);
	}

	public List<BachelorGroupVO> getScheduleCode() {
		return mapper.getScheduleCode();
	}

	@Override
	public BachelorScheduleVO getYearSemester(BachelorScheduleVO vo) {
		return mapper.getYearSemester(vo);

	}
	
	@Override
	public void scheduleInsert(BachelorScheduleVO vo) {
		mapper.scheduleInsert(vo);
	}

	@Override
	public BachelorScheduleVO getScheduleInfo(BachelorScheduleVO vo) {
		return mapper.getScheduleInfo(vo);
	}

	@Override
	public BachelorScheduleVO getCurrentScheduleInfo(BachelorScheduleVO vo) {
		return mapper.getCurrentScheduleInfo(vo);
	}

}
