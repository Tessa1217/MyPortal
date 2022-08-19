package com.project.portal.lecture.service;

import java.util.Date;

import lombok.Data;

@Data
public class VideoVO {
	
	private String videoCode;
	private String videoFilePath;
	private int professorId;
	private String videoName;
	private String videoStoredName;
	private Date videoRegDate;
	private double videoPlayTime;
	private String videoExtension;
	

}
