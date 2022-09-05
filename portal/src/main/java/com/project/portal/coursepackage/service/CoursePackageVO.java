package com.project.portal.coursepackage.service;

import lombok.Data;

@Data
public class CoursePackageVO {

	private String packageYn;
	private String courseCode;
	private String courseName;
	private String courseSortation;
	private int courseCredit;
	private int courseLimit;
	private String professorName;
	private int packageCredit;
	private int courseYear;
	private int courseSemester;
	// 학생 정보 찾기
	private String collegeName;
	private String departName;
	private String departmentCode;
	private String collegeCode;
	private String departCode;
	private String studentName;
	private int studentGrade;
	private int studentId;
}
