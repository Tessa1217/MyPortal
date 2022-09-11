package com.project.portal.exam.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.portal.common.Criteria;
import com.project.portal.course.service.CourseVO;
import com.project.portal.course.service.CourseWeeklyVO;
import com.project.portal.exam.service.CourseExamVO;
import com.project.portal.exam.service.ExamInfoVO;
import com.project.portal.exam.service.ExamScoreVO;
import com.project.portal.exam.service.ExamVO;
import com.project.portal.exam.service.ProfessorExamMapper;
import com.project.portal.exam.service.ProfessorExamService;
import com.project.portal.exam.service.QuestionOptionVO;
import com.project.portal.exam.service.QuestionVO;

@Service
public class ProfessorExamServiceImpl implements ProfessorExamService {

	@Autowired ProfessorExamMapper mapper;
	
	@Override
	public List<ExamVO> getExamList(CourseVO vo, ExamVO exam) {
		return mapper.getExamList(vo, exam);
	}
	
	@Override
	public List<ExamInfoVO> getExamInfoList(ExamInfoVO vo) {
		return mapper.getExamInfoList(vo);
	}

	@Override
	public List<CourseExamVO> getCourseExam(ExamInfoVO vo, List<CourseExamVO> list) {
		return mapper.getCourseExam(vo, list);
	}

	@Override
	public void insertExam(CourseVO course, ExamVO exam) {
		mapper.insertExam(course, exam);	
	}

	@Override
	public List<ExamScoreVO> getExamScore(CourseVO vo, Criteria cri) {
		return mapper.getExamScore(vo, cri);
	}

	@Override
	public void updateExam(ExamVO vo) {
		mapper.updateExam(vo);
	}

	@Override
	public void deleteExam(ExamVO vo) {
		mapper.deleteExam(vo);
	}

	@Override
	@Transactional
	public void createQuestion(QuestionVO vo) {
		mapper.insertQuestion(vo);
		for (QuestionOptionVO option : vo.getOptionList()) {
			option.setQuestionCode(vo.getQuestionCode());
			mapper.insertQuestionOption(option);
		}
	}

	@Override
	@Transactional
	public void insertCourseExam(List<CourseExamVO> list) {
		for (CourseExamVO vo : list) {
			mapper.insertCourseExam(vo);
		}
	}

	@Override
	@Transactional
	public void finalExamSubmit(ExamInfoVO vo, List<ExamScoreVO> studentList) {
		mapper.updateExamStatus(vo);
		mapper.updateCourseExamCompletion(vo);
		for (ExamScoreVO student : studentList) {
			mapper.insertExamScore(student);
		}
	}

	@Override
	public Map<String, CourseWeeklyVO> getTestDate(CourseVO vo, String detailCode1, String detailCode2) {
		Map<String, CourseWeeklyVO> testDates = new HashMap<String, CourseWeeklyVO>();
		testDates.put("middle", mapper.getTestDate(vo, detailCode1));
		testDates.put("final", mapper.getTestDate(vo, detailCode2));
		return testDates;
	}

	@Override
	public int getExamScoreTotal(CourseVO vo, Criteria cri) {
		return mapper.getExamScoreTotal(vo, cri);
	}

	@Override
	public void deleteQuestion(QuestionVO vo) {
		mapper.deleteQuestion(vo);
	}

	@Override
	public void updateQuestion(QuestionVO vo) {
		mapper.updateQuestion(vo);
		
	}
}
