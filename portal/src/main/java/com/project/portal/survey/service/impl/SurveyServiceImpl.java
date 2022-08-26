package com.project.portal.survey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.survey.service.SurveyAnswerVO;
import com.project.portal.survey.service.SurveyMapper;
import com.project.portal.survey.service.SurveyService;
import com.project.portal.survey.service.SurveyVO;

@Service
public class SurveyServiceImpl implements SurveyService {

	@Autowired SurveyMapper mapper;
	@Override
	public SurveyVO selectSurvey(SurveyVO vo) {
		// TODO Auto-generated method stub
		return mapper.selectSurvey(vo);
	}
	@Override
	public SurveyAnswerVO insertSurveyAnswer(SurveyAnswerVO vo) {
		// TODO Auto-generated method stub
		return mapper.insertSurveyAnswer(vo);
	}

}
