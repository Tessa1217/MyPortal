package com.project.portal.course.service;

import java.util.List;

import lombok.Data;

@Data
public class CourseVO {
	
	private String courseCode;
	private int courseYear;	// 강의 연도
	private int courseSemester;	// 강의 학기
	private String courseName;	// 강의 이름
	private String courseSortation; // 강의 이수구분
	private int courseCredit;
	private String precourseCode;
	private int courseLimit;
	private int professorId; // 교수 id
	private String courseSummary;
	private String textbookReference;
	private String courseNotes;
	private int reportAssignedScore;
	private int midtermAssignedScore;
	private int finalAssignedScore;
	private int attAssignedScore;
	private String surveyCode;
	private String departCode;
	// 주차 계획 정보
	private List<CourseWeeklyVO> weekPlans;

}
