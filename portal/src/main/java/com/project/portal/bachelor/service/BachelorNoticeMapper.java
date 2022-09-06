package com.project.portal.bachelor.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BachelorNoticeMapper {
	
	List<BachelorNoticeVO> getNoticeList(BachelorNoticeVO vo);
	void insertNotice(BachelorNoticeVO vo);
	void updateNotice(BachelorNoticeVO vo);
	void deleteNotice(BachelorNoticeVO vo);
	BachelorNoticeFileVO getFile(BachelorNoticeVO vo);
	void insertFile(BachelorNoticeFileVO vo);
	void uploadFile(BachelorNoticeFileVO vo);
	void deleteFile(BachelorNoticeFileVO vo);

}
