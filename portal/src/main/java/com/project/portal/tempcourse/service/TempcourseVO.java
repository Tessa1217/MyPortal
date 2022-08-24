package com.project.portal.tempcourse.service;

import lombok.Data;

@Data
public class TempcourseVO {

	private String courseCode;
	private int courseYear;
	private int courseSemester;
	private String courseName;
	private String courseSortation;
	private int courseCredit;
	private String precourseCode;
	private int courseLimit;
	private String okayYes;
	private String submitYes;
	private String backReason;
	private int professorId;
	private String courseSummary;
	private String courseBook;
	private String textbookReference;
	private int reportAssignedScore;
	private int midtermAssignedScore;
	private int finalAssignedScore;
	private int attAssignedScore;
	private String departCode;
	private String courseNotes;
	
	
}
