package com.project.portal.bachelor.service;

import java.util.Date;

import lombok.Data;

@Data
public class BachelorScheduleVO {
	private String scheduleCode;
	private String scheduleContent;
	private Date scheduleStartDate;
	private Date scheduleEndDate;
	private char scheduleSortCode;
}
