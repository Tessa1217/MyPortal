package com.project.portal.tempcourse.service;





import java.sql.Date;

import lombok.Data;

@Data
public class TempcourseweekVO {

	private String weekCode;
	private String courseCode;
	private int weekNum;
	private String weekContent;
	private Date weekStartDate;
	private Date weekEndDate;
	private int courseYear;
	private int courseSemester;
	
}
