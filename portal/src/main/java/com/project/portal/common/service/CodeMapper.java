package com.project.portal.common.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CodeMapper {
	List<CodeVO> getDetailList(String codeGroup);
}
