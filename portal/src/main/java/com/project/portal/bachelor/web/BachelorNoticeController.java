package com.project.portal.bachelor.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.portal.bachelor.service.BachelorNoticeVO;

@Controller
public class BachelorNoticeController {
	
	@RequestMapping({"/student/notice", "professor/notice", "/admin/notice"})
	public String getNotice(HttpServletRequest request,
							Model model) {
		String requestURI = request.getRequestURI();
		int command = setCommand(requestURI);
		System.out.println(command);
		model.addAttribute("command", command);
		return "common/notice";
	}
	
	@RequestMapping({"/student/detailNotice/{noticeCode}", 
					"/professor/detailNotice/{noticeCode}",
					"/admin/detailNotice/{noticeCode}"
					})
	public String detailNotice(HttpServletRequest request,
							Model model) {
		String requestURI = request.getRequestURI();
		int command = setCommand(requestURI);
		model.addAttribute("command", command);
		return "common/detailNotice";
	}
	
	@RequestMapping("/admin/insertNotice")
	public String insertNoticePage() {
		return "common/insertNotice";
	}
	
	@PostMapping("/admin/insertNotice")
	@ResponseBody
	public String insertNotice(@RequestParam("file") MultipartFile file, 
								BachelorNoticeVO vo) {
		return "success";
	}
	
	@RequestMapping("/admin/updateNotice")
	public String updateNoticePage() {
		return "common/updateNotice";
	}
	
	@PostMapping("/admin/updateNotice")
	@ResponseBody
	public String updateNotice(@RequestParam("file") MultipartFile file,
								BachelorNoticeVO vo) {
		return "success";
	}
	
	@PostMapping("/admin/deleteNotice")
	@ResponseBody
	public String deleteNotice(@RequestParam("file") MultipartFile file,
								BachelorNoticeVO vo) {
		return "success";
	}
	
	private int setCommand(String requestURI) {
		int command = 0;
		if (requestURI.startsWith("/professor/")) {
			command = 2;
		} else if (requestURI.startsWith("/student/")) {
			command = 1;
		} else if (requestURI.startsWith("/admin/")) {
			command = 3;
		}
		return command;
	}
		
}
