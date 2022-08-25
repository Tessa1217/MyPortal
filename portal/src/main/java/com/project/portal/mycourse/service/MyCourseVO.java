package com.project.portal.mycourse.service;



import java.util.Date;

import lombok.Data;

@Data
public class MyCourseVO {
	
	private String courseName; // 강의명
	private String professorName; // 교수이름
	private String courseRegCode; // 강의 
	private String courseCode;  // 강의코드
	private Date applyDate;	// 신청일자
	private String courseReviewYn;	// 강의 평 제출 유무
	private int studentId;	// 학번
	
	
	private int courseYear; // 강의 년도
	private int courseSemester;	// 강의 학기
	private int courseCredit; // 강의 학점
	private double coursePercentage;	// 강의 성적 백분울
	private double courseGrade;	// 강의 성적 평점
	
	
	private int semesterCredit1; // 학기 신청 학점
	private int semesterCredit2; // 학기 취득 학점(F학점 제외)
	private double creditSum; // 학점 합계(학점*평점)+(학점*평점)
	private double gradeAvg; // 학기 평균평점
	private double semesterPercentage; // 학기 백분위
	private int creditSum2; // 총 취득 학점
}
