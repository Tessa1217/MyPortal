package com.project.portal.course.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.portal.bachelor.service.BachelorScheduleVO;
import com.project.portal.common.Criteria;
import com.project.portal.course.service.CourseMapper;
import com.project.portal.course.service.CourseService;
import com.project.portal.course.service.CourseVO;
import com.project.portal.survey.service.SurveyAvgVO;
import com.project.portal.survey.service.SurveyVO;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	CourseMapper mapper;
	
	@Override
	public CourseVO getWeeklyInfo(CourseVO vo) {
		return mapper.getWeeklyInfo(vo);
	}

	@Override
	public List<CourseVO> allCourseList(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.allCourseList(cri);
	}

	@Override
	public List<CourseVO> surveyList(CourseVO vo) {
		// TODO Auto-generated method stub
		return mapper.surveyList(vo);
	}

	@Override
	public SurveyVO surveySelect(SurveyVO vo) {
		// TODO Auto-generated method stub
		return mapper.surveySelect(vo);
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

	@Override
	public CourseVO getYearSemester(BachelorScheduleVO vo) {
		return mapper.getYearSemester(vo);
	}

	@Override
	public int getTotal(CourseVO vo) {
		return mapper.getTotal(vo);
	}
	@Override
	public int getTotal2(CourseVO vo) {
		return mapper.getTotal2(vo);
	}
	
}
