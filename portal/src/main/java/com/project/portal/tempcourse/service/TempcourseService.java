package com.project.portal.tempcourse.service;

import java.util.List;

import com.project.portal.common.Criteria;

public interface TempcourseService {


	public TempcourseVO getTemp(TempcourseVO vo);
	public List<TempcourseVO> tempcourseList(Criteria cri);
	public int tempcourseListCount(Criteria cri);
	public List<TempcourseweekVO> tempcourseweekList(Criteria cri);
	public void tempInsert(TempcourseVO vo);
	public void tempweekInsert(List<TempcourseweekVO> voo);
	
}
