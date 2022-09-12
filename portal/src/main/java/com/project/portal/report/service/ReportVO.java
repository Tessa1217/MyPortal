package com.project.portal.report.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReportVO {
	private String reportCode; // 과제코드
	private String weekCode; // 강의주차계획코드
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date reportRegDate; // 과제 등록일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date reportStartDate; // 과제 시작일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date reportEndDate; // 과제마감일
	private String reportTitle; // 과제 제목
	private String reportContent; // 과제 내용
	private int reportAssignedScore; // 과제 배점
	private String reportFileCode; // 과제파일 코드
	private String userCode;
	private int submitId;
	private ReportFileVO reportFile; //  과제파일
	private String courseCode;  // 강의 코드
	private int weekNum; // 주차
	private String type;
	private String reportSubmissionOption;// 과제 제출 여부
	private int studentId;
	private String reportSubmissionCode; // 과제 제출 코드
	private String reportScoreObjectionApprovalOption; // 이의 신청 승인 여부
	private String reportSubFileCode; //제출된 파일 코드
	private String reportFileName; // 파일 이름
	private int reportNo;// 과제순번
	private int reportScore;// 과제 점수

}
