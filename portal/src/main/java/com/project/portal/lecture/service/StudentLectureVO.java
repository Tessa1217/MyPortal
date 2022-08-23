package com.project.portal.lecture.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class StudentLectureVO {
	private String watchCode;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date watchStartDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date watchEndDate;
	private double watchAccumTime;
	private String lectureCode;
	private int studentId;
	private String attOption;
	private int lectureAttTime;
	// insert/update 나누는 커맨드
	private String cmd;
}
