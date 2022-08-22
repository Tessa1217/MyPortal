package com.project.portal.coursepackage.web;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.course.service.CourseVO;
import com.project.portal.coursepackage.service.CoursePackageService;
import com.project.portal.coursepackage.service.CoursePackageVO;


@Controller
public class CoursePackageController {
	
	@Autowired
	CoursePackageService service;
	
	@RequestMapping("/student/coursePackage")
	public String coursePackage(Model model, CoursePackageVO vo) {
		
		List<CoursePackageVO> coursePackage = service.coursePackage(vo);
		model.addAttribute("coursePackage", coursePackage);
		return "student/register/package";
	}
	
}
