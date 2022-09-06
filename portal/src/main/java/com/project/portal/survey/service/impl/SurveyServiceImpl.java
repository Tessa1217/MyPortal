package com.project.portal.survey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.mycourse.service.MyCourseVO;
import com.project.portal.survey.service.SurveyAnswerVO;
import com.project.portal.survey.service.SurveyMapper;
import com.project.portal.survey.service.SurveyService;
import com.project.portal.survey.service.SurveyVO;

@Service
public class SurveyServiceImpl implements SurveyService {

	@Autowired SurveyMapper mapper;
	@Override
	public SurveyVO selectSurvey(SurveyVO vo) {
		return mapper.selectSurvey(vo);
	}
	@Override
	public void insertSurveyAnswer(SurveyAnswerVO vo) {
		mapper.insertSurveyAnswer(vo);
	}
	@Override
	public void updateSurveyState(MyCourseVO vo) {
		mapper.updateSurveyState(vo);
	}
	@Override
	public MyCourseVO selectSurveyState(MyCourseVO vo) {
		// TODO Auto-generated method stub
		return mapper.selectSurveyState(vo);
	}

}
