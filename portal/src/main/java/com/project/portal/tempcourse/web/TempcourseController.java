package com.project.portal.tempcourse.web;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.portal.common.Criteria;
import com.project.portal.professor.service.ProfessorVO;
import com.project.portal.tempcourse.service.TempcourseService;
import com.project.portal.tempcourse.service.TempcourseVO;
import com.project.portal.tempcourse.service.TempcourseweekVO;

@Controller
public class TempcourseController {

	private static final Logger logger = LoggerFactory.getLogger(TempcourseController.class);

	@Autowired
	TempcourseService service;

	// 단건조회(상세보기)(GET)
	@RequestMapping("/professor/getTemp/{courseCode}")
	public String tempcourse(@PathVariable String courseCode, TempcourseVO vo, Model model, TempcourseweekVO voo) {
		vo = service.getTemp(courseCode);
//		System.out.println(vo.getCourseCode());
//		TempcourseVO tempcourseTest = service.getTemp(vo.getCourseCode());
//		System.out.println(tempcourseTest);
//		List<TempcourseweekVO> tempcourseweekList = service.getTempweek(vo.getCourseCode());
//		System.out.println(tempcourseweekList);
		
		
		
		TempcourseVO vol = new TempcourseVO();
		model.addAttribute("tempcourseList", vol);
		model.addAttribute("tempcourse", service.getTemp(vo.getCourseCode()));
		model.addAttribute("tempcourseweek", service.getTempweek(vo.getCourseCode()));
		
		System.out.println(vo.getCourseCode());
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

		List<TempcourseVO> tempcourseList = service.tempcourseList(vo,cri);
		int total = service.tempcourseListCount(vo,cri);

		model.addAttribute("tempcourseList", tempcourseList);
		model.addAttribute("tempcourse", service.getTemp(vo.getCourseCode()));
		System.out.println(tempcourseList);
		return "professor/course/tempcourseList";
	}

	// 등록페이지
	@RequestMapping("/professor/tempInsert")
	public String tempInsert(TempcourseVO vo, Model model, TempcourseweekVO voo, ProfessorVO pvo, Criteria cri) {
		pvo.setProfessorId(220002);
		pvo = service.getInfo(pvo);
//		voo.setWeekCode("HUEN000804");
//		vo.setProfessorId(0);
//		vo.setCourseCode(null);
		
		List<TempcourseVO> list = service.tempcourseList(vo,cri);
//		List<TempcourseweekVO> list2 = service.tempcourseweekList(null);
		
		model.addAttribute("tempcourseList", list);
//		model.addAttribute("tempcourseWList", list2);
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
	public String tempweekInsertProc(@RequestParam Map map, Model model,TempcourseweekVO voo) {
		
		
		int i = 1;

		/*
		 * for (String weekContent : voo.getWeekContent().split(",")) {
		 * voo.setWeekNum(i); voo.setWeekContent(weekContent);
		 * 
		 * 
		 * service.tempweekInsert(voo); i++; }
		 */

		for(int j = 1;j<=15;j++) {
			voo.setWeekNum(j);
			voo.setWeekContent((String)map.get("weekContent"+j));
			
			service.tempweekInsert(voo);
		}
		return "redirect:professor/tempcourseList";
	}
	
	
	@RequestMapping("professor/getTemp/{courseCode}/updateTemp")
	@ResponseBody
	public int tempUpdate(@PathVariable String courseCode, TempcourseVO vo, Model model) {
		
		System.out.println(courseCode);
		vo.setCourseCode(courseCode);
		System.out.println(vo);
		System.out.println(vo.getCourseSortation());
		return service.updateTemp(vo);
		
	}
	
//	@RequestMapping(value = {"/professor/getTemp/{courseCode}/updateweekTemp", "/tempweekUpdate"})
//	@ResponseBody
//	public int tempweekUpdate(@RequestParam Map map, TempcourseweekVO voo, Model model) {
//		
//		System.out.println(voo);
//		System.out.println(voo.getCourseCode());
//		int i = 1;
//
//		for(int j = 1;j<=15;j++) {
//			voo.setWeekNum(j);
//			voo.setWeekContent((String)map.get("weekContent"+j));
//			
//			service.updateweekTemp(voo);
//			
//		}
//		return service.updateweekTemp(voo);
//			
//	}
	@RequestMapping("professor/getTemp/{courseCode}/updateweekTemp")
	@ResponseBody
	public String updateweekTemp(@PathVariable String courseCode, @RequestParam Map map, Model model,TempcourseweekVO voo, TempcourseVO vo) {
		
		
		
//		voo.setCourseCode(vo.getCourseCode());
		
		model.addAttribute("msg", "변경 내용을 저장하시겠습니까?");
		
		
		TempcourseVO vol = new TempcourseVO();
		model.addAttribute("tempcourseList", vol);
		model.addAttribute("tempcourse", service.getTemp(vo.getCourseCode()));
		model.addAttribute("tempcourseweek", service.getTempweek(vo.getCourseCode()));

		/*
		 * for (String weekContent : voo.getWeekContent().split(",")) {
		 * voo.setWeekNum(i); voo.setWeekContent(weekContent);
		 * 
		 * 
		 * service.tempweekInsert(voo); i++; }
		 */

		for(int j = 1;j<=15;j++) {
			voo.setWeekNum(j);
			voo.setWeekContent((String)map.get("weekContent"+j));
			service.updateweekTemp(voo);
			 
		}
		
		return "";
	}
	
	
	
	@RequestMapping(value ={"/professor/tempcourseList/submitTemp", "/professor/getTemp/submitTemp"})
	@ResponseBody
	public int submitTemp(TempcourseVO vo, Model model) {
		
		
		System.out.println(vo);
		System.out.println(vo.getSubmitYes());
		System.out.println(vo.getBackReason());
		
		return service.submitTemp(vo);
		
	}
	
	// 괸라자 강의계획서 목록(GET)
		@RequestMapping("/admin/adminTempList")
		public String adminTempList(Model model, TempcourseVO vo, Criteria cri) {

			List<TempcourseVO> tempcourseList = service.tempcourseList(vo,cri);
			int total = service.tempcourseListCount(vo,cri);

			model.addAttribute("tempcourseList", tempcourseList);
			model.addAttribute("tempcourse", service.getTemp(vo.getCourseCode()));
			System.out.println(tempcourseList);
			return "admin/info/adminTempList";
		}
	
	

}
