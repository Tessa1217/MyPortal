package com.project.portal.bachelor.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.portal.common.Criteria;

@Mapper
public interface BachelorNoticeMapper {
	
	List<BachelorNoticeVO> getNoticeList(@Param("notice") BachelorNoticeVO vo, 
										@Param("cri") Criteria cri);
	int getTotal(@Param("notice") BachelorNoticeVO vo, 
				@Param("cri") Criteria cri);
	void insertNotice(BachelorNoticeVO vo);
	void updateNotice(BachelorNoticeVO vo);
	void deleteNotice(BachelorNoticeVO vo);
	BachelorNoticeFileVO getFile(BachelorNoticeVO vo);
	void insertFile(BachelorNoticeFileVO vo);
	void deleteFile(BachelorNoticeVO vo);
	void hitIncrease(BachelorNoticeVO vo);

}
