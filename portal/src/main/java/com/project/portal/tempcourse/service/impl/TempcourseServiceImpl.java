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
	public List<TempcourseVO> tempcourseList(TempcourseVO vo, Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.tempcourseList(vo,cri);
	}




	


	@Override
	public List<TempcourseweekVO> tempcourseweekList(TempcourseVO vo, Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.tempcourseweekList(vo, cri);
	}



	@Override
	public int tempcourseListCount(TempcourseVO vo,Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.tempcourseListCount(vo,cri);
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



	@Override
	public List<TempcourseweekVO> getTempweek(String no) {
		// TODO Auto-generated method stub
		return mapper.getTempweek(no);
	}

	@Override
	public TempcourseVO getTemp(String no) {
		// TODO Auto-generated method stub
		return mapper.getTemp(no);
	}







	@Override
	public int updateTemp(TempcourseVO vo) {
		// TODO Auto-generated method stub
		
		return mapper.updateTemp(vo);
	}







	@Override
	public int updateweekTemp(TempcourseweekVO voo) {
		// TODO Auto-generated method stub
		return mapper.updateweekTemp(voo);
	}







	@Override
	public int submitTemp(TempcourseVO vo) {
		// TODO Auto-generated method stub
		 return mapper.submitTemp(vo);
	}







	@Override
	public int okayTemp(TempcourseVO vo) {
		// TODO Auto-generated method stub
		return mapper.okayTemp(vo);
	}







	@Override
	public int backTemp(TempcourseVO vo) {
		// TODO Auto-generated method stub
		return mapper.backTemp(vo);
	}







	@Override
	public List<TempcourseVO> adminTempList(TempcourseVO vo, Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.adminTempList(vo, cri);
	}







	@Override
	public TempcourseVO backReasonWhy(String courseCode) {
		// TODO Auto-generated method stub
		return mapper.backReasonWhy(courseCode);
	}







	@Override
	public TempcourseVO backReasonInsert(TempcourseVO vo) {
		// TODO Auto-generated method stub
		return mapper.backReasonInsert(vo);
	}







	@Override
	public TempcourseVO bringme(TempcourseVO vo) {
		// TODO Auto-generated method stub
		return null;
	}







	@Override
	public List<TempcourseVO> bringme(TempcourseVO vo, Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.bringme(vo, cri);
	}







	






	

	



	
	
	
}
