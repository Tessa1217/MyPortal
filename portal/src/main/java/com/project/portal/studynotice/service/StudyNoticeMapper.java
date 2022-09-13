package com.project.portal.studynotice.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.portal.common.Criteria;

@Mapper
public interface StudyNoticeMapper {

	// 학생 강의 공지사항 조회
	public List<StudyNoticeVO> selectStudyNoticeList(@Param("notice")StudyNoticeVO vo, @Param("cri") Criteria cri);
	// 교수 강의 공지사항 조회
	public List<StudyNoticeVO> selectProStudyNoticeList(@Param("notice")StudyNoticeVO vo, @Param("cri") Criteria cri);
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
	// 교수 공지사항 수정 페이지 이동
	public void modifyStudyNotice(StudyNoticeVO vo);
	// 교수 공지사항 수정 처리
	public void modifyProfStudyNotice(StudyNoticeVO vo);
	// 교수 공지사항 수정 처리(파일 수정포함)
	public void modifyProfStudyNoticeFile(StudyNoticeVO vo);
	// 교수 공지사항 파일 삭제
	public void deleteProfStudyNoticeFile(String fileUrl);
	// 교수 공지사항 파일 업로드
	public void fileUpload(StudyNoticeFileVO vo);
	// 교수 파일 업로드 그룹번호 조회
	public String fileUploadGroupNum();
	// 공지사항 파일 조회
	public List<StudyNoticeFileVO> selectFile(StudyNoticeVO vo);
	// 파일 다운로드
	public StudyNoticeFileVO filedownload(String groupNo);
	// 공지사항 조회수 업데이트
	public void updateHit(String hit);
	// 공지사항 게시글 개수 카운트
	public int getTotal(StudyNoticeVO vo);
	// 게시글에 등록된 파일경로 읽기
	public String[] getInsertFilePath(StudyNoticeVO vo);

	
	
	// 페이징 처리
	public List<StudyNoticeVO> studyNoticePage(Criteria cri);
	// 번호 공지사항 조회
	public List<StudyNoticeVO> selectStudyNoticeNoList(int num);
	// 등록일자 공지사항 조회
	public List<StudyNoticeVO> selectStudyNoticeRegList(Date date);
	// 제목 공지사항 조회
	public List<StudyNoticeVO> selectStudyNoticeTitleList(String title);
}
