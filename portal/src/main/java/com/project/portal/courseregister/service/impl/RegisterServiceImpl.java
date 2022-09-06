package com.project.portal.courseregister.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.courseregister.service.RegisterMapper;
import com.project.portal.courseregister.service.RegisterService;
import com.project.portal.courseregister.service.RegisterVO;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	RegisterMapper mapper;
	
	@Override
	public List<RegisterVO> studentInfo(RegisterVO vo) {
		// TODO Auto-generated method stub
		return mapper.studentInfo(vo);
	}

	@Override
	public List<RegisterVO> registerList(RegisterVO vo) {
		// TODO Auto-generated method stub
		return mapper.registerList(vo);
	}

	@Override
	public RegisterVO registerInsert(RegisterVO vo) {
		// TODO Auto-generated method stub
		//이전 신청 여부 확인
		RegisterVO rVO = mapper.courseCheck(vo);
		if (rVO == null) {
			mapper.registerInsert(vo);
			return mapper.limitCount(vo);	
		}
		return null; 
	}

	@Override
	public List<RegisterVO> packageNList(RegisterVO vo) {
		// TODO Auto-generated method stub
		return mapper.packageNList(vo);
	}

	@Override
	public List<RegisterVO> successList(RegisterVO vo) {
		// TODO Auto-generated method stub
		return mapper.successList(vo);
	}

	@Override
	public int registerDelete(RegisterVO vo) {
		// TODO Auto-generated method stub
		return mapper.registerDelete(vo);
	}

	@Override
	public RegisterVO registerAllCredit(RegisterVO vo) {
		// TODO Auto-generated method stub
		return mapper.registerAllCredit(vo);
	}


	
}
