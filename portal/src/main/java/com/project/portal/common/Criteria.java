package com.project.portal.common;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
public class Criteria {
	
	// Criteria
	private int pageNum; // 페이지 번호
	private int amount; // 한 페이지당 출력할 개수
	private String type; // 검색 타입
	private String keyword; // 검색 키워드
	
	
	// 초기값 지정
	public Criteria() {
		this(1, 10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String getListLink(int pageNum, int amount) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
			.queryParam("pageNum", pageNum)
			.queryParam("amount", amount);
		return builder.toUriString();
	}
	
	public String getLink(int pageNum, int amount, String type, String keyword) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
			.queryParam("pageNum", pageNum)
			.queryParam("amount", amount)
			.queryParam("type", type)
			.queryParam("keyword", keyword);
		return builder.toUriString();
	}
	
	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
	}

}
