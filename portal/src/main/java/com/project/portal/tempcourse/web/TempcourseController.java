package com.project.portal.tempcourse.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.common.Criteria;
import com.project.portal.tempcourse.service.TempcourseService;
import com.project.portal.tempcourse.service.TempcourseVO;

@Controller
public class TempcourseController {

	private static final Logger logger = LoggerFactory.getLogger(TempcourseController.class);
	
	@Autowired
	TempcourseService service;
	
	//단건조회(상세보기)
	@RequestMapping("/professor/getTemp") 
	public String tempcourse(TempcourseVO vo, Model model) {
		model.addAttribute("tempcourse", service.getTemp(vo));
		return "professor/course/getTemp";
	}
	
	//목록
	@RequestMapping("/professor/tempcourseList")
	public String tempcourseList(Model model, TempcourseVO vo, Criteria cri) {
		
		List<TempcourseVO> tempcourseList = service.tempcourseList(cri);
		int total = service.tempcourseListCount(cri);
		
		
		model.addAttribute("tempcourseList", tempcourseList);
		System.out.println(tempcourseList);
		return "professor/course/tempcourseList";
	}
	//주차별
	
	//등록
	@RequestMapping("/professor/tempInsert")
	public String tempInsert(TempcourseVO vo, Model model) {
		
		
		model.addAttribute("tempInfo", vo);
		
		return "professor/course/tempInsert";
	}
	//등록 처리
	@RequestMapping("/tempInsertProc")
	public String tempInsertProc(TempcourseVO vo) {
		logger.debug(vo.toString());
		System.out.println(vo);
		service.tempInsert(vo);
		
		return "professor/course/tempInsert";
	}
	
	
}
