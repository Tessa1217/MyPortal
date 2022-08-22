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
	public StudyNoticeVO selectDetailStudyNotice(String No) {
		// TODO Auto-generated method stub
		return mapper.selectDetailStudyNotice(No);
	}



}
