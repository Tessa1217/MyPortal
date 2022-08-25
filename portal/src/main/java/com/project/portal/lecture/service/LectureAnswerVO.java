package com.project.portal.lecture.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class LectureAnswerVO {
	private String lectureAnswerNum;
	private String lectureQuestionNum;
	private String lectureAnswerContent;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date lectureAnswerRegDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date lectureAnswerModDate;
}
