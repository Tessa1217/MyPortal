package com.project.portal.course.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CourseWeeklyVO {
	
	private String weekCode;
	private String courseCode;
	private int weekNum;
	private String weekContent;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date weekStartDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date weekEndDate;

}
