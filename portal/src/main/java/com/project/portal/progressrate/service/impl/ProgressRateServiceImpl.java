package com.project.portal.progressrate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.progressrate.service.ProgressRateMapper;
import com.project.portal.progressrate.service.ProgressRateService;
import com.project.portal.progressrate.service.ProgressRateVO;

@Service
public class ProgressRateServiceImpl implements ProgressRateService{
	@Autowired
	ProgressRateMapper mapper;
	@Override
	public ProgressRateVO selectWeekAtt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProgressRateVO> selectLectureProgress(ProgressRateVO vo) {
		// TODO Auto-generated method stub
		return mapper.selectLectureProgress(vo);
	}

	@Override
	public List<ProgressRateVO> selectReportProgress(ProgressRateVO vo) {
		// TODO Auto-generated method stub
		return mapper.selectReportProgress(vo);
	}

	@Override
	public List<ProgressRateVO> selectAttProgress(ProgressRateVO vo) {
		// TODO Auto-generated method stub
		return mapper.selectAttProgress(vo);
	}

	@Override
	public ProgressRateVO selectMidExamProgress(ProgressRateVO vo) {
		// TODO Auto-generated method stub
		return mapper.selectMidExamProgress(vo);
	}

	@Override
	public ProgressRateVO selectFinalExamProgress(ProgressRateVO vo) {
		// TODO Auto-generated method stub
		return mapper.selectFinalExamProgress(vo);
	}

	@Override
	public List<ProgressRateVO> selectReportProgressRate(ProgressRateVO vo) {
		// TODO Auto-generated method stub
		return mapper.selectReportProgressRate(vo);
	}
	
}
