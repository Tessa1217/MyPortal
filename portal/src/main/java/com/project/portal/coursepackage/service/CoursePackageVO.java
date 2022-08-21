package com.project.portal.coursepackage.service;

import lombok.Data;

@Data
public class CoursePackageVO {
	
	private String packageYn;
	private String courseCode;
	private int studentId;
	private String courseName;
	private char courseSortation;
	private int courseCredit;
	private int courseLimit;
	private String professorId;
		
}
