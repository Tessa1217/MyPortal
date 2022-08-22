package com.project.portal.tempcourse.service;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import com.project.portal.common.Criteria;

@Mapper
public interface TempcourseMapper {

	
	public TempcourseVO getTemp(TempcourseVO vo);
	public List<TempcourseVO> tempcourseList(Criteria cri);
	public int tempcourseListCount(Criteria cri);
	public List<TempcourseweekVO> tempcourseweekList(Criteria cri);
	public void tempInsert(TempcourseVO vo);
	public void tempweekInsert(TempcourseweekVO voo);
}
