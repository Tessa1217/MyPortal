package com.project.portal.tempcourse.service;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.portal.common.Criteria;
import com.project.portal.professor.service.ProfessorVO;

@Mapper
public interface TempcourseMapper {

	ProfessorVO getInfo(ProfessorVO pvo); //교수 단대 가져오기
	public TempcourseVO getTemp(String no);  //강의계획서 상세정보
	public List<TempcourseweekVO> getTempweek(String no); //강의계획서 상세정보(주차별)
	public List<TempcourseVO> tempcourseList(@Param("vo") TempcourseVO vo,@Param("cri") Criteria cri); // 강의계획서 조회 리스트
	public int tempcourseListCount(@Param("vo") TempcourseVO vo,@Param("cri") Criteria cri); //강의계획서 카운트
	public List<TempcourseweekVO> tempcourseweekList(TempcourseVO vo, Criteria cri); //강의계획서 상세정보(주차별)
	public List<TempcourseweekVO> tempcourseweekListList();
	public void tempInsert(TempcourseVO vo); //강의계획서 입력(기본정보)
	public void tempweekInsert(TempcourseweekVO voo); //강의계획서 입력(주차별)
	public int updateTemp(TempcourseVO vo); //강의계획서 수정(기본정보)
	public int updateweekTemp(TempcourseweekVO voo); //강의계획서 수정(주차별)
	public int submitTemp(TempcourseVO vo); //강의계획서 제출
	public int okayTemp(TempcourseVO vo); //강의계획서 승인 기능
	public int backTemp(TempcourseVO vo); //강의계획서 비승인 기능
	public List<TempcourseVO> adminTempList(@Param("vo") TempcourseVO vo,@Param("cri") Criteria cri); //관리자 강의계획서 리스트(제출된)
	public TempcourseVO backReasonWhy(String courseCode); //강의계획서 비승인 사유보기 (교수)
	public TempcourseVO backReasonInsert(TempcourseVO vo); //강의계획서 비승인 내용 입력( 관리자)
	public List<TempcourseVO> bringme(@Param("vo") TempcourseVO vo,@Param("cri") Criteria cri); //강의계획서입력페이지에서 강의계획서 정보를 모달로 불러오기
	public int okayTempCourse(TempcourseVO vo); // 승인시 강의 테이블로 데이터이관
	public int okayTempCourseWeek(TempcourseweekVO voo); // 승인시 주차별 강의테이블로 데이터 이관
	public int tempDelete(TempcourseVO vo); //강의계획서 삭제
	public TempcourseVO getUpdateTemp(String no); //승인된 강의계획서 상세정보 불러오기
	public void tempInsertWith(TempcourseweekVO voo);
	int getTotal(@Param("vo") TempcourseVO vo, @Param("cri") Criteria cri);
}
