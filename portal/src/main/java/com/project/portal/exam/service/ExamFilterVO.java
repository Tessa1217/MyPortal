package com.project.portal.exam.service;

import java.util.List;

import lombok.Data;

@Data
public class ExamFilterVO {
	ExamInfoVO exam;
	List<CourseExamVO> filterQuestions;
}
