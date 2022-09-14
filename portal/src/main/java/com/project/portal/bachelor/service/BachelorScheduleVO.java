package com.project.portal.bachelor.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BachelorScheduleVO {
	private String scheduleCode;
	private String scheduleContent;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date scheduleStartDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date scheduleEndDate;
	private int year;
	private String detailCode;
	private int semester;
	private int month;
	private int command = 0;
}
