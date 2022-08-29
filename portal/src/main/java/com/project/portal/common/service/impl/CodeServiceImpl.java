package com.project.portal.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.common.service.CodeMapper;
import com.project.portal.common.service.CodeService;
import com.project.portal.common.service.CodeVO;

@Service
public class CodeServiceImpl implements CodeService {

	@Autowired
	CodeMapper mapper;
	
	@Override
	public List<CodeVO> getDetailList(String codeGroup) {
		return mapper.getDetailList(codeGroup);
	}

	@Override
	public Map<String, List<CodeVO>> getAllDetailList(String... codeGroupList) {
		Map<String, List<CodeVO>> map = new HashMap<String, List<CodeVO>>();
		for (String codeGroup : codeGroupList) {
			List<CodeVO> list = mapper.getDetailList(codeGroup);
			map.put(codeGroup, list);
		}
		return map;
	}
	
}
