package com.project.portal.bachelor.service;

import java.util.Date;

import lombok.Data;

@Data
public class BachelorScheduleVO {
	private String scheduleCode;
	private String scheduleContent;
	private Date scheduleStartDate;
	private Date scheduleStartDate2;
	private Date scheduleEndDate;
	private int year;
	private String detailCode;
	private int semester;
	private int month;
	private int command = 0;
}
