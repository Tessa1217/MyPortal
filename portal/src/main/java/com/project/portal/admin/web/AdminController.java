package com.project.portal.admin.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.portal.admin.service.AdminService;
import com.project.portal.admin.service.AdminVO;

//작성자: 김진형
@Controller
public class AdminController {
	
	@Autowired AdminService service;
	
	// 관리자 본인 정보 조회
	@RequestMapping("/admin/adminInfo")
	public String AdminInfo(AdminVO vo, Model model, HttpSession session) {
		vo.setAdminId((int) session.getAttribute("id"));
		model.addAttribute("adminInfo", service.adminInfoSelect(vo));
		return "admin/info/adminPersonal";
	}
	
	@RequestMapping("/admin/adminInfoUpdate")
	@ResponseBody
	public AdminVO adminInfoupdate(AdminVO vo, Model model, HttpSession session) {
		vo.setAdminId((int) session.getAttribute("id"));
		return service.adminInfoUpdate(vo);
	}
	
}
