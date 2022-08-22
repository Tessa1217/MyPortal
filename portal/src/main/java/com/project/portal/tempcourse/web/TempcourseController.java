package com.project.portal.tempcourse.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.common.Criteria;
import com.project.portal.tempcourse.service.TempcourseService;
import com.project.portal.tempcourse.service.TempcourseVO;
import com.project.portal.tempcourse.service.TempcourseweekVO;

@Controller
public class TempcourseController {

	private static final Logger logger = LoggerFactory.getLogger(TempcourseController.class);
	
	@Autowired
	TempcourseService service;
	
	//단건조회(상세보기)(GET)
	@RequestMapping("/professor/getTemp") 
	public String tempcourse(TempcourseVO vo, Model model) {
		model.addAttribute("tempcourse", service.getTemp(vo));
		return "professor/course/getTemp";
	}
	
	//목록(GET)
	@RequestMapping("/professor/tempcourseList")
	public String tempcourseList(Model model, TempcourseVO vo, Criteria cri) {
		
		List<TempcourseVO> tempcourseList = service.tempcourseList(cri);
		int total = service.tempcourseListCount(cri);
		
		
		model.addAttribute("tempcourseList", tempcourseList);
		System.out.println(tempcourseList);
		return "professor/course/tempcourseList";
	}
	
	
	//등록페이지
	@RequestMapping("/professor/tempInsert")
	public String tempInsert(TempcourseVO vo, Model model, TempcourseweekVO voo) {
		List<TempcourseweekVO> list = new ArrayList<TempcourseweekVO>();
		for(int i=1; i<=14; i++) {
			TempcourseweekVO tempcourseweekVO = new TempcourseweekVO();
			tempcourseweekVO.setWeekNum(i);
			list.add(tempcourseweekVO);
		
		}
		model.addAttribute("tempweekInsert", list);
		
		return "professor/course/tempInsert";
	}
	//등록 처리(POST)

	@RequestMapping("/tempInsertProc")
	public String tempInsertProc(TempcourseVO vo, TempcourseweekVO voo, Model model) {
		int i = 1;
		
		for(String weekContent : voo.getWeekContent().split(",")) {
			voo.setWeekNum(i);
			voo.setWeekContent(weekContent);
			voo.setWeekCode("HUEN000803");
			service.tempweekInsert(voo);
			i++;
		}
		
		service.tempInsert(vo);
		
		return "redirect:tempcourseList";
	}
	
	
}
