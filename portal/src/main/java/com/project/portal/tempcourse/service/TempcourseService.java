package com.project.portal.tempcourse.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	public int okayTemp(TempcourseVO vo);
	public int backTemp(TempcourseVO vo);
	public List<TempcourseVO> adminTempList(@Param("vo") TempcourseVO vo,@Param("cri") Criteria cri);
	public TempcourseVO backReasonWhy(String courseCode);
	public TempcourseVO backReasonInsert(TempcourseVO vo);
	public TempcourseVO bringme(TempcourseVO vo);
	public List<TempcourseVO> bringme(@Param("vo") TempcourseVO vo,@Param("cri") Criteria cri); //강의계획서입력페이지에서 강의계획서 정보를 모달로 불러오기
}
