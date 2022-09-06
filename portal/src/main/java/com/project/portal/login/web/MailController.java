package com.project.portal.login.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.portal.login.service.MailVO;
import com.project.portal.login.service.impl.MailService;

@RestController
@RequestMapping("/mail")
public class MailController {
	
	@Autowired
	private MailService mailService;
	
	@PostMapping("/verifyId")
	@ResponseBody
	public String sendVerification(MailVO find) {
		return mailService.verifyId(find.getId());
	}
	
	@PostMapping("/verifyEmail")
	@ResponseBody
	public String sendEmailVerification(MailVO find) {
		return mailService.verifyEmail(find);
	}
	
	@PostMapping("/tempPwd")
	@ResponseBody
	public String sendMail(MailVO mail) {
		mailService.sendMail(mail);
		return "success";
	}
}
