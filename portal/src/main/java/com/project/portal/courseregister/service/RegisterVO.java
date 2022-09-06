package com.project.portal.courseregister.service;

import java.sql.Date;

import lombok.Data;

@Data
public class RegisterVO {

	private int professorId;
	private String packageYn;
	private String courseCode;
	private String courseName;
	private String courseSortation;
	private int courseCredit;
	private int courseLimit;
	private String professorName;
	private int packageCredit;
	private int courseYear;
	private String courseRegCode;
	private Date applyDate;
	private int courseSemester;
	private int studentCount;
	private int year;
	//학생 정보 찾기
	private String collegeName;
	private String departName;
	private String departmentCode;
	private String collegeCode;
	private String departCode;
	private String studentName;
	private int studentGrade;
	private int studentId;
}
