package com.project.portal.common;

import lombok.Data;

@Data
public class Criteria {
	
	// Criteria
	private int pageNum;
	private int amount;
	private String type;
	private String keyword;
	
	
	// 초기값 지정
	public Criteria() {
		this(1, 10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}

}
