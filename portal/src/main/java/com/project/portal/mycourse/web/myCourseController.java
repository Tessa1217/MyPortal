package com.project.portal.mycourse.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.mycourse.service.MyCourseMainVO;
import com.project.portal.mycourse.service.MyCourseVO;
import com.project.portal.mycourse.service.impl.MyCourseServiceImpl;

@Controller
public class myCourseController {
	private static final Logger logger = LoggerFactory.getLogger(myCourseController.class);
	
	@Autowired
	MyCourseServiceImpl service;
	
	// 학생 수강 목록 조회
	
	@RequestMapping("student/courseList")
	public String getstuMyCourse(MyCourseVO vo, Model model) {
		
		List<MyCourseVO> mycourseList = service.getstuMyCourse(vo);
		System.out.println(mycourseList);
		model.addAttribute("mycourseList", service.getstuMyCourse(vo));
		
		return "student/courseList";
	}
	
	
	// 수강 강의 lms 메인 페이지 이동

	@RequestMapping("/student/eclass/{courseCode}")
	public String selectDetailStudyNotice(@PathVariable String courseCode, MyCourseMainVO vo, Model model, HttpSession session) {
		session.setAttribute("courseCode", courseCode);
		System.out.println(courseCode);
	
		
		
		//model.addAttribute("myCourseMain", service.getstuMyCoursePage(courseCode));
		return "student/eclass/eclassmain";
				
	}
	
}


	
