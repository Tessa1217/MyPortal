package com.project.portal.lecture.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class LectureQuestionVO {
	private String lectureQuestionNum;
	private String weekCode;
	private String lectureQuestionTitle;
	private String lectureQuestionContent;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date lectureQuestionRegDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date lectureQuestionModDate;
	private String lectureCode;
	private int studentId;
	private LectureAnswerVO answer;
}
