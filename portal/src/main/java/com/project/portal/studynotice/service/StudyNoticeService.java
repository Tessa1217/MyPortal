package com.project.portal.studynotice.service;

import java.util.Date;
import java.util.List;

import com.project.portal.common.Criteria;

public interface StudyNoticeService {
	
	
	// 학생 강의 공지사항 조회
	public List<StudyNoticeVO> selectStudyNoticeList(StudyNoticeVO vo);
	// 교수 강의 공지사항 조회
	public List<StudyNoticeVO> selectProStudyNoticeList(StudyNoticeVO vo);
	// 공지사항 상세 조회
	public StudyNoticeVO selectDetailStudyNotice(StudyNoticeVO vo);
	// 교수 공지사항 상세 조회
	public StudyNoticeVO selectProDetailStudyNotice(StudyNoticeVO vo);
	// 교수 공지사항 등록페이지
	public StudyNoticeVO insertStudyNoticePage(StudyNoticeVO vo);
	// 교수 공지사항 등록
	public void insertStudyNotice(StudyNoticeVO vo);
	// 교수 공지사항 삭제
	public void deleteStudyNotice(StudyNoticeVO vo);
	// 교수 공지사항 수정
	public void modifyStudyNotice(StudyNoticeVO vo);

	

	
	
	// 페이징 처리
	public List<StudyNoticeVO> studyNoticePage(Criteria cri);
	// 번호 공지사항 조회
	public List<StudyNoticeVO> selectStudyNoticeNoList(int num);
	// 등록일자 공지사항 조회
	public List<StudyNoticeVO> selectStudyNoticeRegList(Date date);
	// 제목 공지사항 조회
	public List<StudyNoticeVO> selectStudyNoticeTitleList(String title);
}
