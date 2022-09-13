package com.project.portal.lecture.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class LectureVO {
	private String lectureCode;
	private String weekCode;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lectureRegDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lectureStartDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lectureEndDate;
	private int lectureAttTime;
	private String lectureContent;
	private String videoCode;
	private String lectureTitle;
	private VideoVO lectureFile;
	private String courseCode;
	private String lectureYoutubePath;
	private String youtubeId;
}
