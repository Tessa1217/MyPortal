package com.project.portal.courseregister.service;

import java.util.List;

public interface RegisterService {
	
	//학생 정보 조회
	public List<RegisterVO> studentInfo(RegisterVO vo);
	
	//강의 목록 조회
	public List<RegisterVO> registerList(RegisterVO vo);
	
	//강의 신청
	public RegisterVO registerInsert(RegisterVO vo);
	
	//꾸러미 실패한 강의 불러오기
	public List<RegisterVO> packageNList(RegisterVO vo);
	
	//신청 성공한 강의 불러오기
	public List<RegisterVO> successList(RegisterVO vo);
	
	//강의 취소
	public int registerDelete(RegisterVO vo);
	
	//수강신청 학점 조회
	public RegisterVO registerAllCredit(RegisterVO vo);
	
}
