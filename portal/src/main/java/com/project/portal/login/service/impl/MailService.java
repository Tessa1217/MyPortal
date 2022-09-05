package com.project.portal.login.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.project.portal.login.service.MailVO;
import com.project.portal.login.service.User;

@Component
public class MailService {
	
	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private UserServiceImpl userService;
	
	public void sendMail(MailVO mail) {
		String tempPwd = insertTempPassword(mail.getId());
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mail.getAddress());
		message.setSubject("[My Portal] 이메일 인증 안내");
		message.setText(mailFormat(tempPwd));
		sender.send(message);
	}
	
	private String insertTempPassword(String userName) {
		User user = new User();
		user.setId(userName);
		String tempPwd = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		String password = scpwd.encode(tempPwd);
		user.setPassword(password);
		userService.setTempPassword(userName, password);
		return tempPwd;
	}
	
	private String mailFormat(String tempPwd) {
		String mailText = "<p>귀하의 임시 비밀번호입니다. 반드시 임시 번호로 로그인 후 변경바랍니다.</p>";
		String temp = "<p>임시 비밀번호: " + tempPwd + "</p>";
		return mailText + temp;
	}
	
	public String verifyId(String id) {
		User user = (User) userService.loadUserByUsername(id);
		if (user.getId() != null) {
			return "success";
		}
		return "error";
	}
	
	public String verifyEmail(MailVO mail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mail.getAddress());
		message.setSubject("[My Portal] 이메일 인증 번호");
		String messageHeader = "<p>이메일 인증 번호 발급 안내입니다.</p>";
		String tempCode = UUID.randomUUID().toString().substring(0, 5);
		String messageBody = "<p>인증 번호를 입력창에 입력해주세요: " + tempCode + "</p>";
		message.setText(messageHeader + messageBody);
		sender.send(message);
		return tempCode;
	}
	
	
}
