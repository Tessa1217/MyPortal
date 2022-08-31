package com.project.portal.login.service;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	
	User getSelectUserInfo(String userId);
	
}
