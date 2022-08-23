package com.project.portal.mycourse.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.mycourse.service.MyCourseMainVO;
import com.project.portal.mycourse.service.MyCourseVO;
import com.project.portal.mycourse.service.myCourseDetailVO;
import com.project.portal.mycourse.service.impl.MyCourseServiceImpl;

@Controller
public class myCourseController {
	private static final Logger logger = LoggerFactory.getLogger(myCourseController.class);
	
	@Autowired
	MyCourseServiceImpl service;
	
	// 학생 수강 목록 조회
	
	@RequestMapping("student/courseList")
	public String getstuMyCourse(MyCourseVO vo, Model model) {
		
		vo.setStudentId(22000001);
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
	
	// 강의 계획서 조회
	@RequestMapping("/student/eclass/courseDetail")
	public String selectCourseDetail(myCourseDetailVO vo, Model model) {
		vo.setCourseCode("SSPY0001");
		System.out.println(vo.getCourseCode());
		myCourseDetailVO myCourseDetailList = service.getstuMyCourseDetail(vo.getCourseCode());
		System.out.println(myCourseDetailList);
		List<myCourseDetailVO> myCourseWeekDetailList = service.getstuMyCourseWeekDetail(vo.getCourseCode());
		System.out.println(myCourseWeekDetailList);
		model.addAttribute("courseDetail" , service.getstuMyCourseDetail(vo.getCourseCode()));
		model.addAttribute("courseWeekDetail", service.getstuMyCourseWeekDetail(vo.getCourseCode()));	
		return "student/eclass/course/courseDetail";
	}
	
}


	
