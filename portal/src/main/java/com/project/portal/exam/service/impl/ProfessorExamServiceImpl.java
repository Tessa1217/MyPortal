package com.project.portal.exam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.portal.course.service.CourseVO;
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
	public List<ExamVO> getExamInfoList(CourseVO vo) {
		return mapper.getExamInfoList(vo);
	}
	
	@Override
	public List<ExamInfoVO> getExamList(ExamInfoVO vo) {
		return mapper.getExamList(vo);
	}

	@Override
	public List<CourseExamVO> getCourseExam(ExamInfoVO vo) {
		return mapper.getCourseExam(vo);
	}

	@Override
	public void insertExam(CourseVO course, ExamVO exam) {
		mapper.insertExam(course, exam);	
	}

	@Override
	public ExamVO getExam(ExamVO vo) {
		return mapper.getExam(vo);
	}

	@Override
	public List<ExamScoreVO> getExamScore(CourseVO vo) {
		return mapper.getExamScore(vo);
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
}
