package com.project.portal.bachelor.service;

import java.util.List;

public interface BachelorNoticeService {
	
	List<BachelorNoticeVO> getNoticeList(BachelorNoticeVO vo);
	void insertNotice(BachelorNoticeVO vo);
	void updateNotice(BachelorNoticeVO vo);
	void updateNoticeOnly(BachelorNoticeVO vo);
	void deleteNotice(BachelorNoticeVO vo);
	BachelorNoticeFileVO getFile(BachelorNoticeVO vo);

}
