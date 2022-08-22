package com.project.portal.tempcourse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.common.Criteria;
import com.project.portal.professor.service.ProfessorVO;
import com.project.portal.tempcourse.service.TempcourseMapper;
import com.project.portal.tempcourse.service.TempcourseService;
import com.project.portal.tempcourse.service.TempcourseVO;
import com.project.portal.tempcourse.service.TempcourseweekVO;

@Service
public class TempcourseServiceImpl implements TempcourseService{

	@Autowired
	TempcourseMapper mapper;
	
	@Override
	public List<TempcourseVO> tempcourseList(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.tempcourseList(cri);
	}



	@Override
	public TempcourseVO getTemp(TempcourseVO vo) {
		// TODO Auto-generated method stub
		return mapper.getTemp(vo);
	}

	


	@Override
	public List<TempcourseweekVO> tempcourseweekList(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.tempcourseweekList(cri);
	}



	@Override
	public int tempcourseListCount(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.tempcourseListCount(cri);
	}



	@Override
	public void tempInsert(TempcourseVO vo) {
		// TODO Auto-generated method stub
		mapper.tempInsert(vo);
	}



	@Override
	public void tempweekInsert(TempcourseweekVO voo) {
		// TODO Auto-generated method stub
		mapper.tempweekInsert(voo);
	}



	@Override
	public ProfessorVO getInfo(ProfessorVO pvo) {
		// TODO Auto-generated method stub
		return mapper.getInfo(pvo);
	}



	



	



	



	



	

	
	
	
}
