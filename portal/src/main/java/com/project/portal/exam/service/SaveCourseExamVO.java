package com.project.portal.exam.service;

import java.util.List;

import lombok.Data;

@Data
public class SaveCourseExamVO {
	List<CourseExamVO> courseExamList;
	String command;
}
