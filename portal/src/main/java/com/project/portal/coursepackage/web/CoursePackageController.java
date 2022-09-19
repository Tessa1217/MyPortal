package com.project.portal.coursepackage.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.portal.bachelor.service.BachelorScheduleService;
import com.project.portal.bachelor.service.BachelorScheduleVO;
import com.project.portal.coursepackage.service.CoursePackageService;
import com.project.portal.coursepackage.service.CoursePackageVO;
import com.project.portal.student.service.StudentService;
import com.project.portal.student.service.StudentVO;

/*
 * 작성자: 송도언
 * 작성일자: 2022/08/25
 * 내용: 수강꾸러미(리스트 조회, 담은 목록, 담기등)에 관한 컨트롤러
 * 수정일자:
 * 수정내용:
*/
@Controller
public class CoursePackageController {

	@Autowired
	CoursePackageService service;
	@Autowired
	StudentService studentService;
	@Autowired
	BachelorScheduleService scheduleService;

	
	@RequestMapping("/student/coursePackage")
	public String coursePackage(Model model, CoursePackageVO vo, HttpSession session, BachelorScheduleVO bvo) {
		vo.setStudentId((int)session.getAttribute("id"));
		vo.setCourseYear((int)session.getAttribute("year"));
		vo.setCourseSemester((int)session.getAttribute("semester"));
		
		// 강의 LIST 조회
		List<CoursePackageVO> coursePackage = service.coursePackage(vo);
		model.addAttribute("coursePackage", coursePackage);

		// 학생 정보
		CoursePackageVO studentInfo = service.studentInfo(vo);
		model.addAttribute("studentInfo", studentInfo);
		
		// 담은 꾸러미 LIST
		List<CoursePackageVO> coursePackageList = service.coursePackageList(vo);
		model.addAttribute("coursePackageList", coursePackageList);

		// 담은 꾸러미 학점
		CoursePackageVO coursePackagePoint = service.coursePackagePoint(vo);
		model.addAttribute("coursePackagePoint", coursePackagePoint);
		
		return "student/register/package";
	}

	// 수강꾸러미 담기
	@RequestMapping("/student/coursePackageInsert")
	@ResponseBody
	public CoursePackageVO CoursePackageInsert(Model model, CoursePackageVO vo, HttpSession session) {
		vo.setStudentId((int)session.getAttribute("id"));
		vo.setCourseYear((int)session.getAttribute("year"));
		vo.setCourseSemester((int)session.getAttribute("semester"));
		return service.coursePackageInsert(vo);
	}

	// 수강꾸러미 선택 삭제
	@RequestMapping("/student/coursePackageDelete")
	@ResponseBody
	public int CoursePackageDelete(Model model, CoursePackageVO vo, HttpSession session) {
		vo.setStudentId((int)session.getAttribute("id"));
		vo.setCourseYear((int)session.getAttribute("year"));
		vo.setCourseSemester((int)session.getAttribute("semester"));
		return service.coursePackageDelete(vo);

	}
}
