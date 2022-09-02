package com.project.portal;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.portal.course.service.CourseVO;
import com.project.portal.report.service.ReportSubmissionVO;
import com.project.portal.report.service.impl.ProfessorReportServiceImpl;

@SpringBootTest
public class ReportTest {

	@Autowired
	ProfessorReportServiceImpl service;
	
	@Test
	public void reportListTest() {
		CourseVO course = new CourseVO();
		course.setCourseCode("SSPY0001");
		List<ReportSubmissionVO> list = service.getStudentReportList(course, null);
		System.out.println(list);
	}
	
}
