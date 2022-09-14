package com.project.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
@EnableScheduling
public class PortalApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
	}
	
}