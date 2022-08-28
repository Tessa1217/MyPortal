package com.project.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class PortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
	}
	////
	@RequestMapping("/student")
	public String home() {
		return "student/main";
	}
	
	@RequestMapping("/professor")
	public String Phome() {
		return "professor/main";
	}
	
	@RequestMapping("/admin")
	public String Ahome() {
		return "admin/main";
	}

}