package com.project.portal.exam.service;

import java.util.List;

import com.project.portal.course.service.CourseVO;

public interface ProfessorExamService {
	
	List<ExamVO> getExamInfoList(CourseVO vo);
	List<ExamInfoVO> getExamList(CourseVO vo);
	List<CourseExamVO> getCourseExam(List<ExamInfoVO> list);

}
