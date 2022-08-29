package com.project.portal.report.service;

import lombok.Data;

@Data
public class ReportSubmissionVO {
	private String reportSubmissionCode;
	private String reportCode;
	private String reportFileCode;
	private String reportSubmissionDate;
	private int reportScore;
	private int studentId;
	private String reportSubmissionOption;
	private ReportFileVO reportFile;
}
