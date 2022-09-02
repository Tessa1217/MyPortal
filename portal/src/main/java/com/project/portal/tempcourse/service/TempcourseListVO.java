package com.project.portal.tempcourse.service;

import java.util.List;

import lombok.Data;

@Data
public class TempcourseListVO {

	private List<TempcourseweekVO> list;
	private String courseCode;
}
