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
	
	// 공지사항 (단건, 목록) 조회 
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
	
	// 공지사항 등록 
	@Override
	@Transactional
	public void insertNotice(BachelorNoticeVO vo) {
		if (vo.getNoticeFile() != null) {
			mapper.insertFile(vo.getNoticeFile());
			vo.setNoticeFileCode(vo.getNoticeFile().getNoticeFileCode());
		}
		mapper.insertNotice(vo);
	}
	
	// 공지사항 수정
	@Override
	@Transactional
	public void updateNotice(BachelorNoticeVO vo, BachelorNoticeFileVO file) {
		if (file != null) {
			if (vo.getNoticeFileCode() != null) {
				mapper.deleteFile(vo);
			}
			mapper.insertFile(file);
			vo.setNoticeFileCode(file.getNoticeFileCode());
		}
		mapper.updateNotice(vo);
	}

	// 공지사항 삭제
	@Override
	@Transactional
	public void deleteNotice(BachelorNoticeVO vo) {
		if (vo.getNoticeFileCode() != null) {
			mapper.deleteFile(vo);
		}
		mapper.deleteNotice(vo);
	}
	
	// 공지사항 파일 가져오기 
	@Override
	public BachelorNoticeFileVO getFile(BachelorNoticeVO vo) {
		return mapper.getFile(vo);
	}
	
	// 공지사항 전체 수 
	@Override
	public int getTotal(BachelorNoticeVO vo, Criteria cri) {
		return mapper.getTotal(vo, cri);
	}
	
	// 공지사항 조회수 
	@Override
	public void hitIncrease(BachelorNoticeVO vo) {
		mapper.hitIncrease(vo);
	}

}
