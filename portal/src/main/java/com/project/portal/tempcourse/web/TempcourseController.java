package com.project.portal.tempcourse.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.portal.bachelor.service.BachelorScheduleService;
import com.project.portal.bachelor.service.BachelorScheduleVO;
import com.project.portal.common.Criteria;
import com.project.portal.common.PageDTO;
import com.project.portal.common.service.CodeService;
import com.project.portal.common.service.CodeVO;
import com.project.portal.professor.service.ProfessorVO;
import com.project.portal.tempcourse.service.TempcourseListVO;
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
	@Autowired
	BachelorScheduleService schedule;
	
	@ModelAttribute("Sortation")
	public List<CodeVO> getSortationCodeList() {
		return codeService.getDetailList("C01");
	}
	
	@ModelAttribute("submitYes")
	public List<CodeVO> getSubmitCodeList() {
		return codeService.getDetailList("S02");
	}
	
	@ModelAttribute("okayYes")
	public List<CodeVO> getOkayCodeList() {
		return codeService.getDetailList("A01");
	}
	
	@ModelAttribute("reList")
	public List<CodeVO> getRejectionList() {
		return codeService.getDetailList("R03");
	}
	
	@ModelAttribute("yearSemester") 
	public BachelorScheduleVO getYearSemester() {
		BachelorScheduleVO s = new BachelorScheduleVO();
		s.setDetailCode("PROF00");
		return schedule.getScheduleInfo(s);
	}
	
	// 단건조회(상세보기)(GET)
	@RequestMapping("/professor/getTemp/{courseCode}")
	public String tempcourse(@PathVariable String courseCode, TempcourseVO vo, Model model, TempcourseweekVO voo,
			Criteria cri, HttpSession session, Authentication authentication, BachelorScheduleVO vooo) {
		vo = service.getTemp(courseCode);

		List<TempcourseVO> list = service.tempcourseList(vo, cri);
		List<TempcourseVO> list2 = service.bringme(vo, cri);
		List<TempcourseweekVO> list3 = service.tempcourseweekList(vo, cri);
		List<TempcourseweekVO> list4 = service.tempcourseweekListList();

		model.addAttribute("tempcourseLis", list);
		model.addAttribute("tempcourseList", list2); // 강의계획서불러오기
		model.addAttribute("tempcourseweekListList", list4); // SemesterVO에서 가져오기
		model.addAttribute("tempcourseweekList", list3); // 강의계획서 주차별 정보 가져오기
		List<TempcourseVO> tempcourseList = service.tempcourseList(vo, cri);
		model.addAttribute("tempcourseList", tempcourseList);
		model.addAttribute("tempcourse", service.getTemp(vo.getCourseCode()));
		model.addAttribute("tempcourseweek", service.getTempweek(vo.getCourseCode()));

		logger.info(vo.getCourseCode());
		return "/professor/course/getTemp";
	}

	// 교수 강의계획서 리스트목록(GET)
	@RequestMapping(value = { "/professor/tempcourseList", "/professor/tempInsert/tempList" })
	public String tempcourseList(Model model, TempcourseVO vo, Criteria cri, HttpSession session,
			Authentication authentication, BachelorScheduleVO voo) {

		vo.setProfessorId((int) session.getAttribute("id"));
		List<TempcourseVO> tempcourseList = service.tempcourseList(vo, cri);
		int total = service.tempcourseListCount(vo, cri);
		vo.setCourseYear((int) session.getAttribute("year"));
		vo.setCourseSemester((int) session.getAttribute("semester"));

		model.addAttribute("pageMaker", new PageDTO(service.getTotal(vo, cri), cri.getAmount(), cri));
		model.addAttribute("year", vo.getCourseYear());
		model.addAttribute("semester", vo.getCourseSemester());
		model.addAttribute("tempcourseList", tempcourseList);
		model.addAttribute("tempcourse", service.getTemp(vo.getCourseCode()));

		return "/professor/course/tempcourseList";
	}

	// 강의계획서 등록페이지
	@RequestMapping("/professor/tempInsert")
	public String tempInsert(TempcourseVO vo, Model model, TempcourseweekVO voo, ProfessorVO pvo, Criteria cri,
			HttpSession session, Authentication authentication, BachelorScheduleVO vooo) {

		vo.setProfessorId((int) session.getAttribute("id"));
		pvo.setProfessorId((int) session.getAttribute("id"));
		pvo = service.getInfo(pvo);
		vooo.setYear((int) session.getAttribute("year"));
		vooo.setSemester((int) session.getAttribute("semester"));

		model.addAttribute("pageMaker", new PageDTO(service.getTotal(vo, cri), cri.getAmount(), cri));

		List<TempcourseVO> list = service.tempcourseList(vo, cri);
		List<TempcourseVO> list2 = service.bringme(vo, cri);
		List<TempcourseweekVO> list3 = service.tempcourseweekList(vo, cri);
		List<TempcourseweekVO> list4 = service.tempcourseweekListList();
		
		model.addAttribute("year", vooo.getYear());
		model.addAttribute("semester", vooo.getSemester());
		model.addAttribute("tempcourseLis", list);
		model.addAttribute("tempcourseList", list2); // 강의계획서불러오기
		model.addAttribute("tempcourseweekListList", list4); // SemesterVO에서 가져오기
		model.addAttribute("tempcourseweekList", list3); // 강의계획서 주차별 정보 가져오기
		model.addAttribute("departInsert", pvo);

		return "/professor/course/tempInsert";
	}

	// 강의계획서 기본정보 등록 처리(POST)
	@PostMapping("/tempInsertProc")
	public String tempInsertProc(TempcourseVO vo, TempcourseweekVO voo, Model model) {
		service.tempInsert(vo);
		return "redirect:/professor/getTemp/" + vo.getCourseCode();
	}

	// 주차별 등록처리
	@PostMapping("/tempweekInsertProc")
	public String tempweekInsertProc(TempcourseListVO vo, Model model) {
		service.tempweekInsert(vo);
		return "redirect:/professor/tempcourseList";
	}

	// 강의계획서 기본정보 수정기능
	@RequestMapping(value = { "/professor/getTemp/{courseCode}/updateTemp",
			"/professor/getUpdateTemp/{courseCode}/updateTemp" })
	@ResponseBody
	public int tempUpdate(@PathVariable String courseCode, TempcourseVO vo, Model model) {
		vo.setCourseCode(courseCode);
		return service.updateTemp(vo);
	}

	// 강의계획서 주차별 강의 수정기능
	@RequestMapping("/professor/getTemp/{courseCode}/updateweekTemp")
	public String updateweekTemp(@PathVariable String courseCode, @RequestParam Map map, Model model,
			TempcourseweekVO voo, TempcourseVO vo) {
		TempcourseVO vol = new TempcourseVO();
		model.addAttribute("tempcourseList", vol);
		model.addAttribute("tempcourse", service.getTemp(vo.getCourseCode()));
		model.addAttribute("tempcourseweek", service.getTempweek(vo.getCourseCode()));
		for (int j = 1; j <= 16; j++) {
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
		return service.submitTemp(vo);
	}

	// 괸라자 강의계획서 목록(GET)
	@RequestMapping("/admin/adminTempList")
	public String adminTempList(Model model, TempcourseVO vo, Criteria cri, HttpSession session,
			BachelorScheduleVO vooo) {
		List<TempcourseVO> tempcourseList = service.adminTempList(vo, cri);
		int total = service.tempcourseListCount(vo, cri);
		vo.setCourseYear((int) session.getAttribute("year"));
		vo.setCourseSemester((int) session.getAttribute("semester"));
		model.addAttribute("pageMaker", new PageDTO(service.getTotal(vo, cri), cri.getAmount(), cri));
		model.addAttribute("year", vo.getCourseYear());
		model.addAttribute("semester", vo.getCourseSemester());
		model.addAttribute("tempcourseList", tempcourseList);
		model.addAttribute("tempcourse", service.getTemp(vo.getCourseCode()));
		return "/admin/info/adminTempList";
	}

	// 관리자 강의계획서 상세보기
	@RequestMapping("/admin/adminGetTemp/{courseCode}")
	public String adminGetTemp(@PathVariable String courseCode, TempcourseVO vo, Model model, TempcourseweekVO voo,
			Criteria cri, BachelorScheduleVO vooo) {
		vo = service.getTemp(courseCode);
		model.addAttribute("tempcourse", service.getTemp(vo.getCourseCode()));
		model.addAttribute("tempcourseweek", service.getTempweek(vo.getCourseCode()));
		logger.info(vo.getCourseCode());
		return "/admin/info/adminGetTemp";
	}

	// 관리자 강의계획서 승인기능
	@RequestMapping(value = { "/admin/adminTempList/okayTemp", "/admin/adminGetTemp/okayTemp" })
	@ResponseBody
	public int okayTemp(TempcourseVO vo, Model model) {
		return service.okayTemp(vo);
	}

	// 관리자가 강의계획서 승인시 course 테이블로 강의 이관
	@RequestMapping(value = { "/admin/adminTempList/okayTempCourse", "/admin/adminGetTemp/okayTempCourse" })
	@ResponseBody
	public int okayTempCourse(TempcourseVO vo, Model model) {
		return service.okayTempCourse(vo);
	}

	// 관리자 강의계획서 비승인 기능
	@PostMapping(value = { "/admin/adminTempList/backTemp", "/admin/adminGetTemp/backTemp" })
	@ResponseBody
	public String backTemp(@RequestBody TempcourseVO vo, Model model) {
		service.backTemp(vo);
		return "success";
	}

	// 비승인 사유(교수)
	@PostMapping(value = { "/professor/tempcourseList/backReasonWhy", 
			"/professor/getTemp/{courseCode}/backReasonWhy",
			"/admin/adminTempList/backReasonWhy" })
	@ResponseBody
	public TempcourseVO backReasonWhy(TempcourseVO vo, Model model) {
		vo = service.backReasonWhy(vo.getCourseCode());
		return vo;
	}

	// 강의계획서 입력에서 모달창 띄워서 해당 교수의 강의계획서 리스트 출력
	@RequestMapping("/professor/tempInsert/bringme")
	public String bringme(Model model, TempcourseVO vo, Criteria cri, HttpSession session,
			Authentication authentication) {
		vo.setProfessorId((int) session.getAttribute("id"));
		List<TempcourseVO> tempcourseList = service.tempcourseList(vo, cri);
		model.addAttribute("tempcourseList", tempcourseList);
		model.addAttribute("tempcourse", service.getTemp(vo.getCourseCode()));
		return "/professor/tempInsert";
	}

	// 강의계획서 삭제기능
	@PostMapping(value = { "/professor/getTemp/tempDelete", "/professor/tempcourseList/tempDelete" })
	@ResponseBody
	public int tempDelete(TempcourseVO vo, Model model) {
		return service.tempDelete(vo);
	}

	// 모달창에서 승인된 강의계획서 바로가기에서 수정기능되는 페이지
	@RequestMapping("/professor/getUpdateTemp/{courseCode}")
	public String getUpdateTemp(@PathVariable String courseCode, TempcourseVO vo, Model model, TempcourseweekVO voo,
			Criteria cri, HttpSession session, Authentication authentication) {
		vo = service.getTemp(courseCode);
		List<TempcourseVO> tempcourseList = service.tempcourseList(vo, cri);
		model.addAttribute("tempcourseList", tempcourseList);
		model.addAttribute("tempcourse", service.getTemp(vo.getCourseCode()));
		model.addAttribute("tempcourseweek", service.getTempweek(vo.getCourseCode()));
		logger.info(vo.getCourseCode());
		return "/professor/course/getUpdateTemp";
	}

	// 승인된 강의계획서 주차별 강의 수정기능
	@RequestMapping("/professor/getUpdateTemp/{courseCode}/updateweekTemp")
	public String updateweekTempU(@PathVariable String courseCode, @RequestParam Map map, Model model,
			TempcourseweekVO voo, TempcourseVO vo) {
		TempcourseVO vol = new TempcourseVO();
		model.addAttribute("tempcourseList", vol);
		model.addAttribute("tempcourse", service.getTemp(vo.getCourseCode()));
		model.addAttribute("tempcourseweek", service.getTempweek(vo.getCourseCode()));
		for (int j = 1; j <= 16; j++) {
			voo.setWeekNum(j);
			voo.setWeekContent((String) map.get("weekContent" + j));
			service.updateweekTemp(voo);
		}
		return "redirect:/professor/getUpdateTemp/" + courseCode;
	}
}
