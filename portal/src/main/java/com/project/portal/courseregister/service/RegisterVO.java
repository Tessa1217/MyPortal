package com.project.portal.courseregister.service;

import lombok.Data;

@Data
public class RegisterVO {

	private int studentId;
	private String courseCode;
	private String courseSortation;
	private String courseName;
	private int courseCredit;
	private int professorId;
	private String professorName;
	private int courseLimit;
	private String studentName;
	private String collegeCode;
	private String departCode;
	private int studentGrade;
	private String collegeName;
	private String departName;
	private String departmentCode;
}
