package com.project.portal.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.admin.service.AdminMapper;
import com.project.portal.admin.service.AdminService;
import com.project.portal.admin.service.AdminVO;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminMapper mapper;
	
	@Override
	public AdminVO adminInfoSelect(AdminVO vo) {
		return mapper.adminInfoSelect(vo);
	}

	@Override
	public AdminVO adminInfoUpdate(AdminVO vo) {
		mapper.adminInfoUpdate(vo);
		return mapper.adminInfoSelect(vo);
	}
	
}
