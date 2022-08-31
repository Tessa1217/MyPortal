package com.project.portal.studynotice.service;

import lombok.Data;

@Data
public class StudyNoticeFileVO {
	private String fno; // 파일번호
	private String fileName; // 저장될파일명
	private String fileOriName; // 원래 파일명
	private String fileUrl; // 파일 경로명
	private String groupNo; // 파일 그룹번호
	
	
	
}
