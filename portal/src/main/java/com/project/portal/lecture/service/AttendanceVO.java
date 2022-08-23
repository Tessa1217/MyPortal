package com.project.portal.lecture.service;

import lombok.Data;

@Data
public class AttendanceVO {
	
	private String courseCode;
	private int studentId;
	private String lectureCode;
	private String attOption;

}
