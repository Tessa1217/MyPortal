package com.project.portal.common;

import lombok.Data;

@Data
public class Criteria {
	
	// Criteria
	private int pageNum; // 페이지 번호
	private int amount; // 한 페이지당 출력할 개수
	private String type; // 검색 타입
	private String keyword; // 검색 키워드?
	
	
	// 초기값 지정
	public Criteria() {
		this(1, 10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}

}
