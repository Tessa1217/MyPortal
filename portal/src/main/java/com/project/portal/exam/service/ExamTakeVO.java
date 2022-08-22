package com.project.portal.exam.service;

import java.util.List;

import lombok.Data;

@Data
public class ExamTakeVO {
	List<StudentExamVO> answer;
	ExamScoreVO score;
}
