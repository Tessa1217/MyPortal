package com.project.portal.course.service;

import lombok.Data;

@Data
public class CourseWeeklyVO {
	
	private String weekCode;
	private String courseCode;
	private int weekNum;
	private String weekContent;

}
