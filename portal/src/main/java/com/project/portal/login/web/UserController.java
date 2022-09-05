package com.project.portal.login.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.login.service.User;

@Controller
public class UserController {
	
	@GetMapping("/")
	public String root() {
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login/login";
	}
	
	@PostMapping("/login_success_handler")
	public String userAccess(HttpSession session, Authentication authentication) {
		authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		session.setAttribute("id", Integer.parseInt(user.getId()));		
		if (user.getUserAuthority().equals("STUDENT")) {
			return "redirect:/student";
		} else if (user.getUserAuthority().equals("PROFESSOR")) {
			return "redirect:/professor";
		} else if (user.getUserAuthority().equals("ADMIN")) {
			return "redirect:/admin";
		}
		return "login/userAccess";
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
