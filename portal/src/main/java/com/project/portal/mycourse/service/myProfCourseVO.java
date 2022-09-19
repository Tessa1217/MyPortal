package com.project.portal.mycourse.service;

import lombok.Data;

@Data
public class myProfCourseVO {
	private String courseName;
	private String professorName;
	private String courseRegCode;
	private String courseCode;  // 강의코드
	private int professorId; // 교수코드
	private String courseYear;
	private String courseSemester;
	private int searchYear;
	private int searchSemester;
}
