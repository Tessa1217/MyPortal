package com.project.portal.mycourse.service;

import lombok.Data;

@Data
public class myCourseDetailVO {
	private String courseName; // 강의명
	private String courseSortation; // 이수구분
	private String courseCode; // 강의코드
	private String departName; // 학과명
	private String professorName; // 교수명
	private int courseCredit; // 학점
	private String professorEmail; // 교수 Email
	private String courseSummary; // 강의 개요
	private String weekCode; // 주차코드
	private int weekNum; // 주차
	private String  weekContent; //강의 범위 및 내용
}
