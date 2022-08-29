package com.project.portal.lecture.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class StudentNoteVO {
	
	private String noteCode;
	private String noteContent;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date noteMake;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date noteUpdate;
	private String lectureCode;
	private int studentId;
	private double noteTime;
}
