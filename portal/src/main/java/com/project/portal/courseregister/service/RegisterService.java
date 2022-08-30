package com.project.portal.courseregister.service;

import java.util.List;

public interface RegisterService {
	
	//학생 정보 조회
	public List<RegisterVO> studentInfo(RegisterVO vo);
	
	//강의 목록 조회
	public List<RegisterVO> registerList(RegisterVO vo);
	
	//강의 담기
	public int registerInsert(RegisterVO vo);
	
	//꾸러미 실패한 과목 불러오기
	public List<RegisterVO> packageNList(RegisterVO vo);
}
