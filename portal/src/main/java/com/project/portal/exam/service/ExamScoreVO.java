package com.project.portal.exam.service;

import lombok.Data;

@Data
public class ExamScoreVO {
	private ExamVO studentExam;
	private int studentId;
	private String studentName;
	private String examCode;
	private int examScore;
	private String examTake;
}
