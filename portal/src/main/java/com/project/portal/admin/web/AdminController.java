package com.project.portal.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.project.portal.admin.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	AdminService service;
	
}
