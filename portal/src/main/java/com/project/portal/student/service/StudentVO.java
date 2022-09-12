package com.project.portal.student.service;

import java.util.Date;

import com.project.portal.common.Criteria;

import lombok.Data;

@Data
public class StudentVO extends Criteria{
	// 학업 정보
	private int studentId;
	private String departCode;
	private String departName;
	private int studentGrade;
	private String studentStatus;
	private String studentDivision;
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
	private String parentsRel;
	private String parentsPhone;
	private String studentImg;
	
	// 공통 코드
	private String parentRelNm;
	private String studentGenderNm;
	private String studentDivisionNm;
	private String studentStatusNm;
	
	
}
