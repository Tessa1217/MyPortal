package com.project.portal.survey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.portal.course.service.CourseVO;
import com.project.portal.survey.service.SurveyAnswerVO;
import com.project.portal.survey.service.SurveyAvgVO;
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
	public void insertSurveyAnswer(SurveyAnswerVO vo) {
		// TODO Auto-generated method stub
		mapper.insertSurveyAnswer(vo);
	}
	
	@Override
	@Transactional
	public SurveyAvgVO getSurveyStats(CourseVO course, SurveyAvgVO vo) {
		// 문항별 강의 평점 평균을 받아옴 
		vo.setQuestionAvg(mapper.getQuestionAvg(course));
		// 전체 강의 문항별 평점 
		vo.setCourseAvg(mapper.getCourseAvg(course));
		// 현재 강의 총 평점 
		vo.setMyCourseAvg(mapper.myCourseAvg(course));
		// 전체 강의 총 평점 
		vo.setAllMyCourseAvg(mapper.allMyCourseAvg());
		return vo;
	}

}
