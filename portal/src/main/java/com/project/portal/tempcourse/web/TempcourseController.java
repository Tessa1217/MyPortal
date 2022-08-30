package com.project.portal.tempcourse.web;

import java.util.ArrayList;
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
import com.project.portal.common.service.CodeService;
import com.project.portal.common.service.CodeVO;
import com.project.portal.professor.service.ProfessorVO;
import com.project.portal.tempcourse.service.TempcourseService;
import com.project.portal.tempcourse.service.TempcourseVO;
import com.project.portal.tempcourse.service.TempcourseweekVO;

//개발자 : 이주호
@Controller
public class TempcourseController {

	private static final Logger logger = LoggerFactory.getLogger(TempcourseController.class);

	@Autowired
	TempcourseService service;
	@Autowired
	CodeService codeService;

	// 단건조회(상세보기)(GET)
	@RequestMapping("/professor/getTemp/{courseCode}")
	public String tempcourse(@PathVariable String courseCode, TempcourseVO vo, Model model, TempcourseweekVO voo,Criteria cri) {
		vo = service.getTemp(courseCode);
//		System.out.println(vo.getCourseCode());
//		TempcourseVO tempcourseTest = service.getTemp(vo.getCourseCode());
//		System.out.println(tempcourseTest);
//		List<TempcourseweekVO> tempcourseweekList = service.getTempweek(vo.getCourseCode());
//		System.out.println(tempcourseweekList);
		List<TempcourseVO> tempcourseList = service.tempcourseList(vo, cri);
		model.addAttribute("tempcourseList", tempcourseList);
		model.addAttribute("Sortation", codeService.getDetailList("C01"));
		model.addAttribute("submitYes", codeService.getDetailList("S02"));
		model.addAttribute("okayYes", codeService.getDetailList("A01"));
		model.addAttribute("tempcourse", service.getTemp(vo.getCourseCode()));
		model.addAttribute("tempcourseweek", service.getTempweek(vo.getCourseCode()));

		logger.info(vo.getCourseCode());
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
		
		
		List<TempcourseVO> tempcourseList = service.tempcourseList(vo, cri);
		int total = service.tempcourseListCount(vo, cri);

		model.addAttribute("tempcourseList", tempcourseList);
		model.addAttribute("tempcourse", service.getTemp(vo.getCourseCode()));
		System.out.println(tempcourseList);
		return "professor/course/tempcourseList";
	}

	// 강의계획서 등록페이지
	@RequestMapping("/professor/tempInsert")
	public String tempInsert(TempcourseVO vo, Model model, TempcourseweekVO voo, ProfessorVO pvo, Criteria cri) {
		pvo.setProfessorId(220002);
		pvo = service.getInfo(pvo);
//		voo.setWeekCode("HUEN000804");
//		vo.setProfessorId(0);
//		vo.setCourseCode(null);

		List<TempcourseVO> list = service.tempcourseList(vo, cri);
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

	// 강의계획서 기본정보 등록 처리(POST)
	@PostMapping("/tempInsertProc")
	public String tempInsertProc(TempcourseVO vo, TempcourseweekVO voo, Model model) {

		service.tempInsert(vo);

		System.out.println(vo);
		System.out.println(voo);

		return "redirect:professor/tempInsert";
	}

	// 주차별 등록처리
	@PostMapping("/tempweekInsertProc")
	public String tempweekInsertProc(@RequestParam Map map, Model model, TempcourseweekVO voo) {

		int i = 1;

		/*
		 * for (String weekContent : voo.getWeekContent().split(",")) {
		 * voo.setWeekNum(i); voo.setWeekContent(weekContent);
		 * 
		 * 
		 * service.tempweekInsert(voo); i++; }
		 */

		for (int j = 1; j <= 15; j++) {
			voo.setWeekNum(j);
			voo.setWeekContent((String) map.get("weekContent" + j));

			service.tempweekInsert(voo);
		}
		return "redirect:professor/tempcourseList";
	}

	// 강의계획서 기본정보 수정기능
	@RequestMapping("professor/getTemp/{courseCode}/updateTemp")
	@ResponseBody
	public int tempUpdate(@PathVariable String courseCode, TempcourseVO vo, Model model) {

		vo.setCourseCode(courseCode);
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

	// 강의계획서 주차별 강의 수정기능
	@RequestMapping("professor/getTemp/{courseCode}/updateweekTemp")
	public String updateweekTemp(@PathVariable String courseCode, @RequestParam Map map, Model model,
			TempcourseweekVO voo, TempcourseVO vo) {

//		voo.setCourseCode(vo.getCourseCode());

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

		for (int j = 1; j <= 15; j++) {
			voo.setWeekNum(j);
			voo.setWeekContent((String) map.get("weekContent" + j));
			service.updateweekTemp(voo);

		}

		return "redirect:/professor/getTemp/" + courseCode;
	}

	// 강의계획서 제출기능
	@RequestMapping(value = { "/professor/tempcourseList/submitTemp", "/professor/getTemp/submitTemp" })
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

		List<TempcourseVO> tempcourseList = service.adminTempList(vo, cri);
		int total = service.tempcourseListCount(vo, cri);

		model.addAttribute("tempcourseList", tempcourseList);
		model.addAttribute("tempcourse", service.getTemp(vo.getCourseCode()));
		System.out.println(tempcourseList);
		return "admin/info/adminTempList";
	}

	// 관리자 강의계획서 상세보기
	@RequestMapping("/admin/adminGetTemp/{courseCode}")
	public String adminGetTemp(@PathVariable String courseCode, TempcourseVO vo, Model model, TempcourseweekVO voo, Criteria cri) {
			vo = service.getTemp(courseCode);
//			System.out.println(vo.getCourseCode());
//			TempcourseVO tempcourseTest = service.getTemp(vo.getCourseCode());
//			System.out.println(tempcourseTest);
//			List<TempcourseweekVO> tempcourseweekList = service.getTempweek(vo.getCourseCode());
//			System.out.println(tempcourseweekList);
//			List<TempcourseVO> tempcourseList = service.tempcourseList(vo, cri);
//			int total = service.tempcourseListCount(vo, cri);

//			model.addAttribute("tempcourseList", tempcourseList);
			model.addAttribute("Sortation", codeService.getDetailList("C01"));
			model.addAttribute("submitYes", codeService.getDetailList("S02"));
			model.addAttribute("okayYes", codeService.getDetailList("A01"));
			model.addAttribute("tempcourse", service.getTemp(vo.getCourseCode()));
			model.addAttribute("tempcourseweek", service.getTempweek(vo.getCourseCode()));

		logger.info(vo.getCourseCode());
		return "admin/info/adminGetTemp";
	}
	// 관리자 강의계획서 승인기능
		@RequestMapping(value = { "/admin/adminTempList/okayTemp", "/admin/adminGetTemp/okayTemp" })
		@ResponseBody
		public int okayTemp(TempcourseVO vo, Model model) {

			System.out.println(vo);
			System.out.println(vo.getSubmitYes());
			System.out.println(vo.getBackReason());

			return service.okayTemp(vo);

		}
		
		// 관리자 강의계획서 비승인 기능
				@RequestMapping(value = { "/admin/adminTempList/backTemp", "/admin/adminGetTemp/backTemp" })
				@ResponseBody
				public int backTemp(TempcourseVO vo, Model model) {

					System.out.println(vo);
					System.out.println(vo.getSubmitYes());
					System.out.println(vo.getBackReason());

					return service.backTemp(vo);

				}

		//비승인 사유
		@PostMapping("/professor/tempcourseList/backReasonWhy")
		@ResponseBody
		public TempcourseVO backReasonWhy(TempcourseVO vo, Model model ) {
			
			System.out.println(vo);
			vo=service.backReasonWhy(vo.getCourseCode());
			
			return vo;
		}
}
