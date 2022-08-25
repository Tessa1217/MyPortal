package com.project.portal.tempcourse.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.portal.common.Criteria;
import com.project.portal.professor.service.ProfessorVO;

@Mapper
public interface TempcourseMapper {

	ProfessorVO getInfo(ProfessorVO pvo);
	public TempcourseVO getTemp(String no);
	public List<TempcourseweekVO> getTempweek(String no);
	public List<TempcourseVO> tempcourseList(@Param("vo") TempcourseVO vo,@Param("cri") Criteria cri);
	public int tempcourseListCount(@Param("vo") TempcourseVO vo,@Param("cri") Criteria cri);
	public List<TempcourseweekVO> tempcourseweekList(TempcourseVO vo, Criteria cri);
	public void tempInsert(TempcourseVO vo);
	public void tempweekInsert(TempcourseweekVO voo);
	public int updateTemp(TempcourseVO vo);
}
