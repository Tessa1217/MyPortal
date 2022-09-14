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

	@Override
	public List<BachelorScheduleVO> getMonthlyList(BachelorScheduleVO vo) {
		return mapper.getMonthlyList(vo);
	}

	@Override
	public BachelorScheduleVO gradeYearSemester(BachelorScheduleVO vo) {
		return mapper.gradeYearSemester(vo);
	}

	@Override
	public BachelorScheduleVO yearSemester(BachelorScheduleVO vo) {
		return mapper.yearSemester(vo);
	}

	@Override
	public BachelorScheduleVO packageDate(BachelorScheduleVO vo) {
		return mapper.packageDate(vo);
	}

	@Override
	public BachelorScheduleVO registerDate(BachelorScheduleVO vo) {
		return mapper.registerDate(vo);
	}

	public List<BachelorGroupVO> getScheduleCode() {
		return mapper.getScheduleCode();
	}

	@Override
	public BachelorScheduleVO getYearSemester(BachelorScheduleVO vo) {
		return mapper.getYearSemester(vo);

	}

}
