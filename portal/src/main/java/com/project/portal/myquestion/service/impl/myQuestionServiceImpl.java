package com.project.portal.myquestion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.myquestion.service.myQuestionAnswerVO;
import com.project.portal.myquestion.service.myQuestionMapper;
import com.project.portal.myquestion.service.myQuestionService;
import com.project.portal.myquestion.service.myQuestionVO;

@Service
public class myQuestionServiceImpl implements myQuestionService {

	
	@Autowired
	myQuestionMapper mapper;
	@Override
	public List<myQuestionVO> getStuMyQuestion(String studentId) {
		// TODO Auto-generated method stub
		return mapper.getStuMyQuestion(studentId);
	}
	@Override
	public List<myQuestionVO> getProfMyQuestion(String courseCode) {
		// TODO Auto-generated method stub
		return mapper.getProfMyQuestion(courseCode);
				
	}
	@Override
	public myQuestionVO getStuMyQuestionDetail(String questionNum) {
		// TODO Auto-generated method stub
		return mapper.getStuMyQuestionDetail(questionNum);
	}
	@Override
	public myQuestionVO getStuMyQuestionCheck(String studentId) {
		// TODO Auto-generated method stub
		return mapper.getStuMyQuestionCheck(studentId);
	}
	@Override
	public myQuestionVO getProfMyQuestionDetail(String questionNum) {
		// TODO Auto-generated method stub
		return mapper.getProfMyQuestionDetail(questionNum);
	}
	@Override
	public myQuestionAnswerVO insertProfMyQuestion(myQuestionAnswerVO vo) {
		// TODO Auto-generated method stub
		return mapper.insertProfMyQuestion(vo);
		
	}
	@Override
	public myQuestionAnswerVO modifyProfMyQuestion(myQuestionAnswerVO vo) {
		// TODO Auto-generated method stub
		return mapper.modifyProfMyQuestion(vo);
	}

}
