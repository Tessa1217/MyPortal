package com.project.portal.course.service;


import java.util.List;

import com.project.portal.common.Criteria;

import lombok.Data;


@Data
public class CourseVO extends Criteria {
	
	private String courseCode;
	private int courseYear;	// 강의 연도
	private int courseSemester;	// 강의 학기
	private String courseName;	// 강의 이름
	private String courseSortation; // 강의 이수구분
	private int courseCredit; // 강의 학점
	private String precourseCode;
	private int courseLimit; // 강의 최대 수강생 인원
	private int professorId; // 교수 id
	private double surveyAvg; // 수강평 평점 평균
	private int reviewSubCount; // 수강평 작성 인원수
	private int countAllStudent; // 수강생 인원수
	private int studentId;
	
	private String courseSummary;
	private String textbookReference;
	private String courseNotes;
	private int reportAssignedScore; // 과제 배점
	private int midtermAssignedScore;// 중간고사 배점
	private int finalAssignedScore; // 기말고사 배점
	private int attAssignedScore; // 출석 배점
	private String surveyCode;
	private String departCode;
	
	// 공통 코드
	private String courseSortationNm; // 강의 이수구분
	
	
	// 주차 계획 정보
	private List<CourseWeeklyVO> weekPlans;
	
	// 주차 정보
	private String weekCode;
	private int weekNum;
	private String type;
	
	// 수강 강의 목록 검색 조건 정보
	private String searchYear;
	private String searchSemester;
	
//	// 강의별 강의평가 평균점수
//	private List<CourseSurveyAvgVO> courseSurveyAvg;

}
