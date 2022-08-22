package com.project.portal.studynotice.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class StudyNoticeVO {
	private String courseNoticeNo; // 강의 공지사항 번호
	private String courseCode; // 강의 코드
	private String courseNoticeTitle; // 강의 공지사항 제목
	private String courseNoticeContent; // 강의 공지사항 내용
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date courseNoticeRegDate; // 강의 공지사항 등록일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date courseNoticeUpdateDate; // 강의 공지사항 변경일
	private String courseNoticeAttachedFile; // 강의 공지사항 첨부파일
	private int courseNoticeHit; // 공지사항 조회 수
	
	
	
	
}
