package com.project.portal.tempcourse.service;

import java.util.List;


import com.project.portal.common.Criteria;
import com.project.portal.professor.service.ProfessorVO;

public interface TempcourseService {

	
	ProfessorVO getInfo(ProfessorVO pvo);
	public TempcourseVO getTemp(String no);
	public List<TempcourseweekVO> getTempweek(String no);
	public List<TempcourseVO> tempcourseList(TempcourseVO vo,Criteria cri);
	public int tempcourseListCount(TempcourseVO vo,Criteria cri);
	public List<TempcourseweekVO> tempcourseweekList(TempcourseVO vo,Criteria cri);
	public void tempInsert(TempcourseVO vo);
	public void tempweekInsert(TempcourseweekVO voo);
	public int updateTemp(TempcourseVO vo);
	public int updateweekTemp(TempcourseweekVO voo);
	public int submitTemp(TempcourseVO vo);
}
