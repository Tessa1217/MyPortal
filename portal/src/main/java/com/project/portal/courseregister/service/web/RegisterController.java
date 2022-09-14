package com.project.portal.courseregister.service.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.portal.bachelor.service.BachelorScheduleService;
import com.project.portal.bachelor.service.BachelorScheduleVO;
import com.project.portal.courseregister.service.RegisterService;
import com.project.portal.courseregister.service.RegisterVO;

/*
 * 작성자: 송도언
 * 작성일자: 2022/08/25
 * 내용: 수강신청(학생정보, 리스트 조회, 신청목록, 신청한 목록 등)에 관한 컨트롤러
 * 수정일자:
 * 수정내용:
*/

@Controller
public class RegisterController {

	@Autowired
	RegisterService service;
	@Autowired
	BachelorScheduleService scheduleService;
	

	// 학생 정보, 강의 리스트, 신청한 강의 리스트
	@RequestMapping("/student/courseRegister")
	public String Register(Model model, RegisterVO vo, HttpSession session, BachelorScheduleVO bvo) {

		vo.setStudentId((int) session.getAttribute("id"));
		vo.setCourseYear((int) session.getAttribute("year"));
		vo.setCourseSemester((int) session.getAttribute("semester"));
		System.out.println(vo);
		List<RegisterVO> studentInfo = service.studentInfo(vo);
		model.addAttribute("studentInfo", studentInfo);

		List<RegisterVO> registerList = service.registerList(vo);
		model.addAttribute("registerList", registerList);

		List<RegisterVO> packageNList = service.packageNList(vo);
		model.addAttribute("packageNList", packageNList);

		List<RegisterVO> successList = service.successList(vo);
		model.addAttribute("successList", successList);

		RegisterVO registerAllCredit = service.registerAllCredit(vo);
		model.addAttribute("registerAllCredit", registerAllCredit);
		
//		BachelorScheduleVO packageDate = scheduleService.packageDate(bvo);
//		model.addAttribute("packageDate", packageDate);
//		
//		BachelorScheduleVO registerDate = scheduleService.registerDate(bvo);
//		model.addAttribute("registerDate", registerDate);
		
		return "student/register/register";
	}

	// 강의 신청
	@RequestMapping("/student/registerInsert")
	@ResponseBody
	public RegisterVO registerInsert(Model model, RegisterVO vo, HttpSession session) {
		vo.setStudentId((int) session.getAttribute("id"));
		vo.setCourseYear((int) session.getAttribute("year"));
		vo.setCourseSemester((int) session.getAttribute("semester"));
		return service.registerInsert(vo);
	}

	// 강의 취소
	@RequestMapping("/student/registerDelete")
	@ResponseBody
	public int RegisterDelete(Model model, RegisterVO vo, BachelorScheduleVO bvo, HttpSession session) {
		vo.setStudentId((int) session.getAttribute("id"));
		vo.setCourseYear((int) session.getAttribute("year"));
		vo.setCourseSemester((int) session.getAttribute("semester"));
		return service.registerDelete(vo);
	}
}
