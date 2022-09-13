package com.project.portal.report.service;

import lombok.Data;

@Data
public class ReportFileVO {
	private String reportFileCode;
	private String reportFileName;
	private String reportFileStoredName;
	private String reportFileExtension;
	private String reportFilePath;
	private String userCode;
	private String reportCode;
	private String reportSubFileCode;
	private int submitId;
	private int studentId;
	private String courseCode;
	private int courseYear;
	private int courseSemester;
	private String courseName;
}
