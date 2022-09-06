package com.project.portal.bachelor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.portal.bachelor.service.BachelorNoticeFileVO;
import com.project.portal.bachelor.service.BachelorNoticeMapper;
import com.project.portal.bachelor.service.BachelorNoticeService;
import com.project.portal.bachelor.service.BachelorNoticeVO;

@Service
public class BachelorNoticeServiceImpl implements BachelorNoticeService {

	@Autowired
	BachelorNoticeMapper mapper;
	
	@Override
	public List<BachelorNoticeVO> getNoticeList(BachelorNoticeVO vo) {
		List<BachelorNoticeVO> noticeList = mapper.getNoticeList(vo);
		for (BachelorNoticeVO notice : noticeList) {
			if (notice.getNoticeFile() != null) {
				BachelorNoticeFileVO file = mapper.getFile(vo);
				notice.setNoticeFile(file);
			}
		}
		return noticeList;
	}

	@Override
	@Transactional
	public void insertNotice(BachelorNoticeVO vo) {
		mapper.insertNotice(vo);
		mapper.insertFile(vo.getNoticeFile());
	}

	@Override
	@Transactional
	public void updateNotice(BachelorNoticeVO vo) {
		mapper.updateNotice(vo);
		mapper.uploadFile(vo.getNoticeFile());
	}

	@Override
	@Transactional
	public void deleteNotice(BachelorNoticeVO vo) {
		mapper.deleteFile(vo.getNoticeFile());
		mapper.deleteNotice(vo);
	}

	@Override
	public void updateNoticeOnly(BachelorNoticeVO vo) {
		mapper.updateNotice(vo);
	}

	@Override
	public BachelorNoticeFileVO getFile(BachelorNoticeVO vo) {
		return mapper.getFile(vo);
	}

}
