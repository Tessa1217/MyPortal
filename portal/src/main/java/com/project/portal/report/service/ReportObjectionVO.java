package com.project.portal.report.service;

import lombok.Data;

@Data
public class ReportObjectionVO {
	// 이의신청 학생 정보
	private int studentGrade; // 학년
	private int studentId; // 학번
	private String studentName; // 이름
	
	// 이의신청 과제 정보
	private int weekNum; // 주차
	private String courseName; // 강의명
	private int reportScore; // 처음 평가된 과제 점수
	
	private String reportSubmissionCode; // 과제 제출코드
	private String reportScoreObjectionApplicationReason;// 이의신청 사유
	
}
