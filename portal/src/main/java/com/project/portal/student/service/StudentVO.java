package com.project.portal.student.service;

import java.util.Date;

import lombok.Data;

@Data
public class StudentVO {
	// 학업 정보
	private int studentId;
	private char departCode;
	private String departName;
	private int studentGrade;
	private char studentStatus;
	private char studentDivision;
	private Date studentAdmissionDate;
	private Date studentGraduationDate;
	
	// 개인 정보
	private String studentName;
	private Date studentBirth;
	private char studentGender;
	private String studentAddr;
	private String studentDaddr;
	private int studentPostalCode;
	private String studentPhone;
	private String studentEmail;
	private String parentsName;
	private char parentsRel;
	private String parentsPhone;
	private String studentImg;
	
}
