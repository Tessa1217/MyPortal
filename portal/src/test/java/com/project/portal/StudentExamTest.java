package com.project.portal;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.portal.exam.service.CourseExamVO;
import com.project.portal.exam.service.ExamResultVO;
import com.project.portal.exam.service.ExamVO;
import com.project.portal.exam.service.StudentExamMapper;

@SpringBootTest
public class StudentExamTest {
	
	@Autowired
	StudentExamMapper mapper;
	
	public void test2() {
		ExamVO vo = new ExamVO();
		vo.setExamCode("MSSPY0001222");
		List<CourseExamVO> list = mapper.getStudentExam(vo);
		System.out.println(list);
	}
	
	public void resultTest() {
		ExamVO vo = new ExamVO();
		vo.setExamCode("MSSPY0001222");
		int studentId = 22000001;
		List<ExamResultVO> list = mapper.getExamResult(studentId, vo);
		System.out.println(list);
	}
}
