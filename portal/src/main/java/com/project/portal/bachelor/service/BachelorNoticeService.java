package com.project.portal.bachelor.service;

import java.util.List;

import com.project.portal.common.Criteria;

public interface BachelorNoticeService {
	
	List<BachelorNoticeVO> getNoticeList(BachelorNoticeVO vo, Criteria cri);
	int getTotal();
	void insertNotice(BachelorNoticeVO vo);
	void updateNotice(BachelorNoticeVO vo);
	void updateNoticeOnly(BachelorNoticeVO vo);
	void deleteNotice(BachelorNoticeVO vo);
	BachelorNoticeFileVO getFile(BachelorNoticeVO vo);

}
