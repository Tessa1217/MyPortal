package com.project.portal.report.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReportVO {
	private String reportCode;
	private String weekCode;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date reportRegDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date reportStartDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date reportEndDate;
	private String reportTitle;
	private String reportContent;
	private int reportAssignedScore;
	private String reportFileCode;
	private ReportFileVO reportFile;
	private String courseCode;

}
