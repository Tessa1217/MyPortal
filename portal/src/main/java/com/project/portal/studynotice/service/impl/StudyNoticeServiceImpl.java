package com.project.portal.studynotice.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.common.Criteria;
import com.project.portal.studynotice.service.StudyNoticeMapper;
import com.project.portal.studynotice.service.StudyNoticeService;
import com.project.portal.studynotice.service.StudyNoticeVO;


@Service
public class StudyNoticeServiceImpl implements StudyNoticeService{

	@Autowired StudyNoticeMapper mapper;
	
	@Override
	public List<StudyNoticeVO> selectStudyNoticeList(StudyNoticeVO vo) {
		// TODO Auto-generated method stub
		return mapper.selectStudyNoticeList(vo);
	}

	@Override
	public List<StudyNoticeVO> studyNoticePage(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.studyNoticePage(cri);
	}

	@Override
	public List<StudyNoticeVO> selectStudyNoticeNoList(int num) {
		// TODO Auto-generated method stub
		return mapper.selectStudyNoticeNoList(num);
	}

	@Override
	public List<StudyNoticeVO> selectStudyNoticeRegList(Date date) {
		// TODO Auto-generated method stub
		return mapper.selectStudyNoticeRegList(date);
	}

	@Override
	public List<StudyNoticeVO> selectStudyNoticeTitleList(String title) {
		// TODO Auto-generated method stub
		return mapper.selectStudyNoticeTitleList(title);
	}

	@Override
	public StudyNoticeVO selectDetailStudyNotice(StudyNoticeVO vo) {
		// TODO Auto-generated method stub
		return mapper.selectDetailStudyNotice(vo);
	}

	@Override
	public List<StudyNoticeVO> selectProStudyNoticeList(StudyNoticeVO vo) {
		// TODO Auto-generated method stub
		return mapper.selectProStudyNoticeList(vo);
	}

	@Override
	public StudyNoticeVO selectProDetailStudyNotice(StudyNoticeVO vo) {
		// TODO Auto-generated method stub
		return mapper.selectProDetailStudyNotice(vo);
	}

	@Override
	public void insertStudyNotice(StudyNoticeVO vo) {
		// TODO Auto-generated method stub
		mapper.insertStudyNotice(vo);
	}

	@Override
	public StudyNoticeVO insertStudyNoticePage(StudyNoticeVO vo) {
		// TODO Auto-generated method stub
		return mapper.insertStudyNoticePage(vo);
	}

	@Override
	public void deleteStudyNotice(StudyNoticeVO vo) {
		// TODO Auto-generated method stub
		mapper.deleteStudyNotice(vo);
	}



}
