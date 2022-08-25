package com.project.portal.coursepackage.service;

import lombok.Data;

@Data
public class CoursePackageVO {
	
	private String packageYn;
	private String courseCode;
	private String courseName;
	private char courseSortation;
	private int courseCredit;
	private int courseLimit;
	private String professorName;
	private int studentId;
	private String collegeCode;
	private String departCode;	
}
