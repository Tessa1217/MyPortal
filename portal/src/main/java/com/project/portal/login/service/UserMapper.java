package com.project.portal.login.service;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
	
	User getSelectUserInfo(String userId);
	void setTempPassword(@Param("id") String userId, 
						@Param("pwd") String tempPwd);
	
}
