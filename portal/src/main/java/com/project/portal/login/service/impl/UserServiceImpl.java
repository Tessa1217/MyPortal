package com.project.portal.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.portal.login.service.User;
import com.project.portal.login.service.UserMapper;
import com.project.portal.login.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired 
	UserMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = mapper.getSelectUserInfo(username);
		return user;
	}
	
	public void setTempPassword(String id, String pwd) {
		mapper.setTempPassword(id, pwd);
	}

}
