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
	private int submitId;
}
