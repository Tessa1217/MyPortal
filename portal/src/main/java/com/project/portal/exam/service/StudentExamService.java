package com.project.portal.exam.service;

import java.util.List;

import com.project.portal.course.service.CourseVO;

public interface StudentExamService {
	
	List<ExamVO> getExamInfo(CourseVO vo);
	List<CourseExamVO> getStudentExam(ExamVO vo);

}
