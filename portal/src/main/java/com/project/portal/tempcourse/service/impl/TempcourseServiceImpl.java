package com.project.portal.tempcourse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.portal.common.Criteria;
import com.project.portal.professor.service.ProfessorVO;
import com.project.portal.tempcourse.service.TempcourseListVO;
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
		
		
		
		  TempcourseweekVO voo = new TempcourseweekVO(); 
		  voo.setCourseYear(vo.getCourseYear());
		  voo.setCourseSemester(vo.getCourseSemester());
		  for(int i=1; i<=16; i++) {
			  voo.setCourseCode(vo.getCourseCode()); 
			  voo.setWeekNum(i);
			  mapper.tempweekInsert(voo);
		  }
	}



	@Override
	public void tempweekInsert(TempcourseListVO vo) {
		// TODO Auto-generated method stub
		for(TempcourseweekVO voo: vo.getList()) {
			voo.setCourseCode(vo.getCourseCode());
			mapper.tempweekInsert(voo);
		}
		
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
	@Transactional
	public int okayTemp(TempcourseVO vo) {
		// TODO Auto-generated method stub
		int update= mapper.okayTemp(vo);
		mapper.okayTempCourse(vo);
		return update;
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







	@Override
	public int okayTempCourse(TempcourseVO vo) {
		// TODO Auto-generated method stub
		return mapper.okayTempCourse(vo);
	}







	@Override
	public List<TempcourseweekVO> tempcourseweekListList() {
		// TODO Auto-generated method stub
		return mapper.tempcourseweekListList();
	}







	@Override
	public int tempDelete(TempcourseVO vo) {
		// TODO Auto-generated method stub
		return mapper.tempDelete(vo);
	}







	@Override
	public TempcourseVO getUpdateTemp(String no) {
		// TODO Auto-generated method stub
		return mapper.getUpdateTemp(no);
	}







	@Override
	public void tempInsertWith(TempcourseweekVO voo) {
		// TODO Auto-generated method stub
		
		mapper.tempInsertWith(voo);
	}







	@Override
	public int getTotal(TempcourseVO vo, Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.getTotal(vo, cri);
	}



















	







	






	

	



	
	
	
}
