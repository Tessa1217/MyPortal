package com.project.portal;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.portal.tempcourse.service.TempcourseMapper;
import com.project.portal.tempcourse.service.TempcourseVO;
import com.project.portal.tempcourse.service.TempcourseweekVO;

@SpringBootTest
public class TempcourseTest {

	@Autowired
	TempcourseMapper mapper;
	
	
	public void test() {
		
		TempcourseVO vo = new TempcourseVO();
	
		vo.setCourseCode(null);
		vo.setCourseYear(2022);
		vo.setCourseSemester(1);
		vo.setDepartCode("ENGL");
		
		mapper.tempInsert(vo);
	}
	
//	@Test
//	public void test1() {
//		
//		List<TempcourseweekVO> list = new ArrayList<TempcourseweekVO>();
//		TempcourseweekVO vo = new TempcourseweekVO();
//		
//		vo.setCourseCode("HUEN0008");
//		vo.setWeekContent(null);
//		vo.setWeekNum(1);
//		
//		
//		
//		list.add(vo);
//		
//		
//		mapper.tempweekInsert(list);
//		
//	}
	
	
}
