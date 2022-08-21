package com.project.portal.courseregister.service;

import lombok.Data;

@Data
public class RegisterVO {

	private int studentId;
	private String courseCode;
	private String courseSortation;
	private String courseName;
	private int courseCredit;
	private String professorName;
	private int courseLimit;
}
