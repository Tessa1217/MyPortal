package com.project.portal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.portal.bachelor.service.BachelorDetailVO;
import com.project.portal.bachelor.service.BachelorGroupVO;
import com.project.portal.bachelor.service.BachelorScheduleMapper;
import com.project.portal.bachelor.service.BachelorScheduleVO;

@SpringBootTest
public class BachelorScheduleTest {
	
	@Autowired
	BachelorScheduleMapper mapper;
	
	
	public void codeTest() {
		List<BachelorGroupVO> groupList = mapper.getScheduleCode();
		for (BachelorGroupVO group : groupList) {
			for (BachelorDetailVO detail : group.getDetailList()) {
				System.out.println(detail);
			}
		}
	}
	
	public void dateTest() {
		BachelorScheduleVO vo = new BachelorScheduleVO();
		Map<String, BachelorScheduleVO> scheduleMap = new HashMap<String, BachelorScheduleVO>();
		vo.setDetailCode("REG00");
		scheduleMap.put("pkg", mapper.getScheduleInfo(vo));
		vo.setDetailCode("REG01");
		scheduleMap.put("reg", mapper.getScheduleInfo(vo));
		System.out.println(scheduleMap);
	}
}
