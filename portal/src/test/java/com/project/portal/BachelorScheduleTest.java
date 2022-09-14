package com.project.portal;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.portal.bachelor.service.BachelorDetailVO;
import com.project.portal.bachelor.service.BachelorGroupVO;
import com.project.portal.bachelor.service.BachelorScheduleMapper;

@SpringBootTest
public class BachelorScheduleTest {
	
	@Autowired
	BachelorScheduleMapper mapper;
	
	@Test
	public void codeTest() {
		List<BachelorGroupVO> groupList = mapper.getScheduleCode();
		for (BachelorGroupVO group : groupList) {
			for (BachelorDetailVO detail : group.getDetailList()) {
				System.out.println(detail);
			}
		}
	}
}
