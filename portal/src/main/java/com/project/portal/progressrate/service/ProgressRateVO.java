package com.project.portal.progressrate.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ProgressRateVO {
	private int studentId; // 학번
	private String courseCode; // 강의코드
	private int weekNum; // 주차
	private int attOption; // 출석여부
	private String weekCode; // 주차코드
	private String lectureTitle; // 강의주제
	private double lecProgressRate; // 강의 진행률
	private String reportTitle; // 과제명
	private String reportSubmissionOption; // 제출 여부
	private String reportCode; // 과제코드
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date reportStartDate; // 과제 시작일
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date reportEndDate; // 과제 시작일
	private int examScore; // 시험 점수
	private String examCode; // 시험 코드
	private int examDuration; // 시험타이머시간
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date examStartDate; // 시험 시작날짜
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date examEndDate; // 시험 종료날짜
	private double reportRate; // 과제 진행률
	private String lectureCode; // 강의영상코드
	private int reportScore;
	private String reportFileCode; //제출파일코드
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date weekStartDate; // 강의 시작 날짜
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date weekEndDate; // 강의 종료 날짜
	
	
}