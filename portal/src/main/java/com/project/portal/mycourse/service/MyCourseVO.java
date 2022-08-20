package com.project.portal.mycourse.service;



import java.util.Date;

import lombok.Data;

@Data
public class MyCourseVO {
	
	private String courseName;
	private String professorName;
	private String courseRegCode;
	private String courseCode;
	private Date applyDate;
	private int coursePercentage;
	private int courseGrade;
	private String courseReviewYn;
	private int studentId;
}
