package com.project.portal.exam.service;

import lombok.Data;

@Data
public class NewQuestionVO {
	private QuestionVO question;
	private QuestionOptionVO optionList;
}
