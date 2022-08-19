package com.project.portal.course.service;

import java.util.List;

import lombok.Data;

@Data
public class CourseVO {
	
	private String courseCode;
	private int courseYear;
	private int courseSemester;
	private String courseName;
	private char courseSortation;
	private int courseCredit;
	private String precourseCode;
	private int courseLimit;
	private String professorId;
	private String courseSummary;
	private String textbookReference;
	private String courseNotes;
	private int reportAssignedScore;
	private int midtermAssignedScore;
	private int finalAssignedScore;
	private int attAssignedScore;
	private String surveyCode;
	private char departCode;
	// 二쇱감 怨꾪쉷 由ъ뒪�듃
	private List<CourseWeeklyVO> weekPlans;

}
