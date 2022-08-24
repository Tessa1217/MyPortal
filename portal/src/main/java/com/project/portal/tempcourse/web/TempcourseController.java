package com.project.portal.tempcourse.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.common.Criteria;
import com.project.portal.professor.service.ProfessorVO;
import com.project.portal.tempcourse.service.TempcourseVO;
import com.project.portal.tempcourse.service.TempcourseweekVO;
import com.project.portal.tempcourse.service.impl.TempcourseServiceImpl;

@Controller
public class TempcourseController {

	private static final Logger logger = LoggerFactory.getLogger(TempcourseController.class);

	@Autowired
	TempcourseServiceImpl service;

	// 단건조회(상세보기)(GET)
	@RequestMapping("/professor/getTemp/{courseCode}")
	public String tempcourse(@PathVariable String courseCode, TempcourseVO vo, Model model, TempcourseweekVO voo) {
		vo = service.getTemp(courseCode);
		System.out.println(vo.getCourseCode());
		TempcourseVO tempcourseTest = service.getTemp(vo.getCourseCode());
		System.out.println(tempcourseTest);
		List<TempcourseweekVO> tempcourseweekList = service.getTempweek(vo.getCourseCode());
		System.out.println(tempcourseweekList);

		model.addAttribute("tempcourse", service.getTemp(vo.getCourseCode()));
		model.addAttribute("tempcourseweek", service.getTempweek(vo.getCourseCode()));
		return "professor/course/getTemp";
	}

//	//단건조회 주차별(상세보기)(GET)
//	@RequestMapping("/professor/getTemp/{courseCode}") 
//	public String tempcourseweek(@PathVariable String courseCode, Model model, TempcourseweekVO voo, TempcourseVO vo) {
//		
//		voo = service.getTempweek(courseCode);
//		
//		
//		model.addAttribute("tempcourseweek", service.getTemp(courseCode));
//		System.out.println(voo);
//		return "professor/course/getTemp";
//	}

	// 목록(GET)
	@RequestMapping("/professor/tempcourseList")
	public String tempcourseList(Model model, TempcourseVO vo, Criteria cri) {

		List<TempcourseVO> tempcourseList = service.tempcourseList(cri);
		int total = service.tempcourseListCount(cri);

		model.addAttribute("tempcourseList", tempcourseList);
		System.out.println(tempcourseList);
		return "professor/course/tempcourseList";
	}

	// 등록페이지
	@RequestMapping("/professor/tempInsert")
	public String tempInsert(TempcourseVO vo, Model model, TempcourseweekVO voo, ProfessorVO pvo) {
		pvo.setProfessorId(220002);
		pvo = service.getInfo(pvo);
		List<TempcourseVO> list = service.tempcourseList(null);

		model.addAttribute("tempcourseList", list);
		model.addAttribute("departInsert", pvo);

		return "professor/course/tempInsert";
	}

//	//등록 처리(POST)
//
//	@RequestMapping("/tempInsertProc")
//	public String tempInsertProc(TempcourseVO vo, TempcourseweekVO voo, Model model) {
//		
//		System.out.println(vo);
//		System.out.println(voo);
//		
//			service.tempInsert(vo);
//			service.tempweekInsert(voo);
//			
//		
//		return "redirect:tempcourseList";
//	}
	// 등록 처리(POST)

	@RequestMapping("/tempInsertProc")
	public String tempInsertProc(TempcourseVO vo, TempcourseweekVO voo, Model model) {

		service.tempInsert(vo);

		System.out.println(vo);
		System.out.println(voo);

		return "redirect:professor/tempInsert";
	}

	@PostMapping("/tempweekInsertProc")
	public String tempweekInsertProc(TempcourseweekVO voo, Model model) {
		
		System.out.println(voo);
		int i = 1;

		for (String weekContent : voo.getWeekContent().split(",")) {
			voo.setWeekNum(i);
			voo.setWeekContent(weekContent);
			
			
			service.tempweekInsert(voo);
			i++;
		}

		return "redirect:professor/tempcourseList";
	}

}
