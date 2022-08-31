package com.project.portal.login.web;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.portal.login.service.User;

@Controller
public class UserController {
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
}
