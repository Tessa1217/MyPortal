package com.project.portal.tempcourse.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.portal.common.Criteria;
import com.project.portal.professor.service.ProfessorVO;

@Mapper
public interface TempcourseMapper {

	ProfessorVO getInfo(ProfessorVO pvo);
	public TempcourseVO getTemp(String no);
	public TempcourseweekVO getTempweek(String no);
	public List<TempcourseVO> tempcourseList(Criteria cri);
	public int tempcourseListCount(Criteria cri);
	public List<TempcourseweekVO> tempcourseweekList(Criteria cri);
	public void tempInsert(TempcourseVO vo);
	public void tempweekInsert(TempcourseweekVO voo);
}
