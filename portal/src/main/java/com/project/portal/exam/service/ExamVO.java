package com.project.portal.exam.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ExamVO {
	
	private String examCode;
	private String weekCode;
	private Date examRegDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date examStartDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date examEndDate;
	private int examDuration;
	private int examTotalQuestion;
	private int examTotalScore;
	private String examSubmit;
	private String courseCode;
}
