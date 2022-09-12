package com.project.portal.professor.service;

import java.util.Date;

import com.project.portal.common.Criteria;

import lombok.Data;

@Data
public class ProfessorVO extends Criteria {
	private int professorId;
	private String departCode;
	private String departName;
	private String professorName;
	private Date professorBirth;
	private String professorAddr;
	private String professorDaddr;
	private int professorPostalCode;
	private String professorPhone;
	private String professorEmail;
	private String professorImg;
	private Date registerDate;
}
