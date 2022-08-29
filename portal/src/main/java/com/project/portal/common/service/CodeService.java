package com.project.portal.common.service;

import java.util.List;
import java.util.Map;

public interface CodeService {
	
	List<CodeVO> getDetailList(String codeGroup);
	Map<String, List<CodeVO>> getAllDetailList(String ... codeGroupList);

}
