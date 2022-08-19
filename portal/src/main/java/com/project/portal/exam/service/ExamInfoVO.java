package com.project.portal.exam.service;

import lombok.Data;

@Data
public class ExamInfoVO {
	
	// 시험지 기본 정보에 들어가는 테이블로 exam_info_vu에서 가져오는 정보
	private int courseYear;
	private int courseSemester;
	private String courseName;
	private String courseCode;
	private String weekCode;
	private String examCode;
	
}
