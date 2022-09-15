package com.project.portal.login.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.bachelor.service.BachelorScheduleService;
import com.project.portal.bachelor.service.BachelorScheduleVO;
import com.project.portal.login.service.User;

@Controller
// 작성자: 권유진

public class UserController {
	
	@Autowired BachelorScheduleService service;
	
	@GetMapping("/")
	public String root() {
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login/login";
	}
	
	@PostMapping("/login_success_handler")
	public String userAccess(HttpSession session, Authentication authentication, BachelorScheduleVO vo) {
		authentication = SecurityContextHolder.getContext().getAuthentication();
		
		// 유저 아이디 세션 등록
		User user = (User) authentication.getPrincipal();
		session.setAttribute("id", Integer.parseInt(user.getId()));
		
		// 현재 년도 학기 세션 등록
		vo.setDetailCode("BPLAN00");
		BachelorScheduleVO svo = service.getScheduleInfo(vo);
		session.setAttribute("year", svo.getYear());
		session.setAttribute("semester", svo.getSemester());
		
		if (user.getUserAuthority().equals("STUDENT")) {
			return "redirect:/student";
		} else if (user.getUserAuthority().equals("PROFESSOR")) {
			return "redirect:/professor";
		} else if (user.getUserAuthority().equals("ADMIN")) {
			return "redirect:/admin";
		}
		
		return "login/userAccess";
	}
	
	@RequestMapping("/findPassword")
	public String findPassword() {
		return "login/findPassword";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, 
			HttpServletResponse response,
			Authentication auth,
			HttpSession session) {
		auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			session.removeAttribute("id");
			if (session.getAttribute("courseCode") != null) {
				session.removeAttribute("courseCode");
			}
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
	}
}
