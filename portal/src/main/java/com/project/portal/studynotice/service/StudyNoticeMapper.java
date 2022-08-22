package com.project.portal.studynotice.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.portal.common.Criteria;

@Mapper
public interface StudyNoticeMapper {

	// 학생 강의 공지사항 조회
	public List<StudyNoticeVO> selectStudyNoticeList(StudyNoticeVO vo);
	
	// 페이징 처리
	public List<StudyNoticeVO> studyNoticePage(Criteria cri);
	
	// 공지사항 상세 조회
		public StudyNoticeVO selectDetailStudyNotice(String No);
	// 번호 공지사항 조회
	public List<StudyNoticeVO> selectStudyNoticeNoList(int num);
	// 등록일자 공지사항 조회
	public List<StudyNoticeVO> selectStudyNoticeRegList(Date date);
	// 제목 공지사항 조회
	public List<StudyNoticeVO> selectStudyNoticeTitleList(String title);
}
