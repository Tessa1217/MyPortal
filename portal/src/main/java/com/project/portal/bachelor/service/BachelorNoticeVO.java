package com.project.portal.bachelor.service;

import java.util.Date;

import lombok.Data;

@Data
public class BachelorNoticeVO {
	private String noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private char noticePrivate;
	private char noticeDivision;
	private Date noticePostDate;
	private Date noticeModDate;
	private String noticeFileCode;
	private BachelorNoticeFileVO noticeFile;
	
}
