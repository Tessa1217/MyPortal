package com.project.portal.myquestion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.common.Criteria;
import com.project.portal.myquestion.service.myQuestionAnswerVO;
import com.project.portal.myquestion.service.myQuestionMapper;
import com.project.portal.myquestion.service.myQuestionService;
import com.project.portal.myquestion.service.myQuestionVO;

@Service
public class myQuestionServiceImpl implements myQuestionService {

	
	@Autowired
	myQuestionMapper mapper;
	@Override
	public List<myQuestionVO> getStuMyQuestion(myQuestionVO vo, Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.getStuMyQuestion(vo, cri);
	}
	@Override
	public List<myQuestionVO> getProfMyQuestion(myQuestionVO vo , Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.getProfMyQuestion(vo, cri);
				
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
	public void insertProfMyQuestion(myQuestionAnswerVO vo) {
		// TODO Auto-generated method stub
		mapper.insertProfMyQuestion(vo);
		
	}
	@Override
	public void modifyProfMyQuestion(myQuestionAnswerVO vo) {
		// TODO Auto-generated method stub
		mapper.modifyProfMyQuestion(vo);
	}
	@Override
	public int getProfTotal(myQuestionVO vo) {
		// TODO Auto-generated method stub
		return mapper.getProfTotal(vo);
	}
	@Override
	public int getStuTotal(myQuestionVO vo) {
		// TODO Auto-generated method stub
		return mapper.getStuTotal(vo);	
	}

}
