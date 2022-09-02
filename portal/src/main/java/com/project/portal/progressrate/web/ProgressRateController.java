package com.project.portal.progressrate.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.progressrate.service.ProgressRateService;

@Controller
public class ProgressRateController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(ProgressRateController.class);
	
	@Autowired ProgressRateService Pservice;
	
	@RequestMapping("/student/eclass/progressRate")
	public String stuProgressRate () {
		
		return "/student/eclass/progressrate/progressrate";
	}
	
	
}
