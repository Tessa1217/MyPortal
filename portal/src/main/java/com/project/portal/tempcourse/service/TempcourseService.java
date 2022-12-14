package com.project.portal.tempcourse.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.portal.bachelor.service.BachelorNoticeVO;
import com.project.portal.common.Criteria;
import com.project.portal.professor.service.ProfessorVO;

public interface TempcourseService {

	
	ProfessorVO getInfo(ProfessorVO pvo);
	public TempcourseVO getTemp(String no);
	public List<TempcourseweekVO> getTempweek(String no);
	public List<TempcourseVO> tempcourseList(TempcourseVO vo,Criteria cri);
	public int tempcourseListCount(TempcourseVO vo,Criteria cri);
	public List<TempcourseweekVO> tempcourseweekList(TempcourseVO vo,Criteria cri);
	public List<TempcourseweekVO> tempcourseweekListList();
	public void tempInsert(TempcourseVO vo);
	public void tempweekInsert(TempcourseListVO vo);
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
	public int okayTempCourse(TempcourseVO vo); // 승인시 강의 테이블로 데이터이관
	public int okayTempCourseWeek(TempcourseweekVO voo); // 승인시 주차별 강의테이블로 데이터 이관
	public int tempDelete(TempcourseVO vo); //강의계획서 삭제
	public TempcourseVO getUpdateTemp(String no); //승인된 강의계획서 상세정보 불러오기
	public void tempInsertWith(TempcourseweekVO voo);
	int getTotal(TempcourseVO vo, Criteria cri);
}
