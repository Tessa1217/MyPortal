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
			if (notice.getNoticeFileCode() != null) {
				notice.setNoticeFile(mapper.getFile(notice));
			}
		}
		return noticeList;
	}

	@Override
	@Transactional
	public void insertNotice(BachelorNoticeVO vo) {
		if (vo.getNoticeFile() != null) {
			mapper.insertFile(vo.getNoticeFile());
		}
		vo.setNoticeFileCode(vo.getNoticeFile().getNoticeFileCode());
		mapper.insertNotice(vo);
	}

	@Override
	@Transactional
	public void updateNotice(BachelorNoticeVO vo, BachelorNoticeFileVO file) {
		if (vo.getNoticeFileCode() != null) {
			mapper.deleteFile(vo);
		}
		mapper.insertFile(file);
		vo.setNoticeFileCode(file.getNoticeFileCode());
		System.out.println(vo);
		mapper.updateNotice(vo);
	}

	@Override
	@Transactional
	public void deleteNotice(BachelorNoticeVO vo) {
		if (vo.getNoticeFileCode() != null) {
			mapper.deleteFile(vo);
		}
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
	public int getTotal(Criteria cri) {
		return mapper.getTotal(cri);
	}

	@Override
	public void hitIncrease(BachelorNoticeVO vo) {
		mapper.hitIncrease(vo);
	}

}
