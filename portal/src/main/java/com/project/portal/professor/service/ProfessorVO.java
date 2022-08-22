package com.project.portal.professor.service;

import java.util.Date;

import lombok.Data;

@Data
public class ProfessorVO {
	private int professorId;
	private String departCode;
	private String professorName;
	private Date professorBirth;
	private String professorAddr;
	private String professorDaddr;
	private int professorPostalCode;
	private String professorPhone;
	private String professorEmail;
	private String professorImg;
}
