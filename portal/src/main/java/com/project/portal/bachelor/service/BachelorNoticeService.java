package com.project.portal.bachelor.service;

import java.util.List;

import com.project.portal.common.Criteria;

public interface BachelorNoticeService {
	
	List<BachelorNoticeVO> getNoticeList(BachelorNoticeVO vo, Criteria cri);
	int getTotal(BachelorNoticeVO vo, Criteria cri);
	void insertNotice(BachelorNoticeVO vo);
	void updateNotice(BachelorNoticeVO vo, BachelorNoticeFileVO file);
	void deleteNotice(BachelorNoticeVO vo);
	BachelorNoticeFileVO getFile(BachelorNoticeVO vo);
	void hitIncrease(BachelorNoticeVO vo);
}
