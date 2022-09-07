package com.project.portal.bachelor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.portal.bachelor.service.BachelorNoticeFileVO;
import com.project.portal.bachelor.service.BachelorNoticeMapper;
import com.project.portal.bachelor.service.BachelorNoticeService;
import com.project.portal.bachelor.service.BachelorNoticeVO;
import com.project.portal.common.Criteria;

@Service
public class BachelorNoticeServiceImpl implements BachelorNoticeService {

	@Autowired
	BachelorNoticeMapper mapper;
	
	@Override
	public List<BachelorNoticeVO> getNoticeList(BachelorNoticeVO vo, Criteria cri) {
		List<BachelorNoticeVO> noticeList = mapper.getNoticeList(vo, cri);
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
		if (vo.getNoticeFile() != null) {
			mapper.insertFile(vo.getNoticeFile());
		}
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

	@Override
	public int getTotal() {
		return mapper.getTotal();
	}

}
