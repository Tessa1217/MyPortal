package com.project.portal;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.portal.common.service.CodeVO;
import com.project.portal.common.service.impl.CodeServiceImpl;

@SpringBootTest
public class CodeTest {
	
	@Autowired 
	CodeServiceImpl service;
	
	// 단건의 그룹코드로 상세 코드 리스트 조회
	//@Test
	public void codeTest() {
		List<CodeVO> codeList = service.getDetailList("C01");
		System.out.println(codeList);
	}
	
	// 여러 건의 그룹코드로 관련 그룹코드의 상세 코드 리스트 전부 조회
	//@Test
	public void codeListTest() {
		Map<String, List<CodeVO>> map = service.getAllDetailList("C01", "S01");
		System.out.println(map.get("C01"));
		System.out.println(map.get("S01"));
	}
}
