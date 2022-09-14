package com.project.portal.bachelor.service;

import java.util.List;

import lombok.Data;

@Data
public class BachelorGroupVO {
	
	private String groupCode;
	private String groupName;
	private String groupDescription;
	private List<BachelorDetailVO> detailList;

}
