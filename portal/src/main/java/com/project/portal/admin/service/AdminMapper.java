package com.project.portal.admin.service;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
	
	AdminVO adminInfoSelect(AdminVO vo);
	void updateAdminInfo(AdminVO vo);
	
}
