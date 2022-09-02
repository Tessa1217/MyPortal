package com.project.portal.report.service;

import com.project.portal.student.service.StudentVO;

import lombok.Data;

@Data
public class ReportSubmissionVO {
	private String reportSubmissionCode; // 과제 제출 코드
	private String reportCode; // 과제 코드
	private String reportFileCode; // 과제 제출 파일 코드
	private String reportSubmissionDate; //과제 제출일
	private int reportScore; // 과제 성적
	private int studentId; // 학번
	private String reportSubmissionOption; //과제 제출 여부
	private ReportFileVO reportFile; //제출 파일명
	private StudentVO studentInfo; // 학생 정보
}
