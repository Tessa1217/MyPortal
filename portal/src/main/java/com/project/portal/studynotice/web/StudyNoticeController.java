package com.project.portal.studynotice.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.portal.studynotice.service.StudyNoticeService;
import com.project.portal.studynotice.service.StudyNoticeVO;
import com.project.portal.tempcourse.web.TempcourseController;

@Controller
public class StudyNoticeController {
	private static final Logger logger = LoggerFactory.getLogger(TempcourseController.class);
	
	@Autowired StudyNoticeService service;
	
	// 학생 강의 공지사항 조회
	@RequestMapping("/student/eclass/eclassnotice")
	public String selectStudyNoticeList(@ModelAttribute("search") StudyNoticeVO vo, Model model) {
		
		vo.setCourseCode("SSPY0001");
		//List<StudyNoticeVO> studyNoticeList = service.selectStudyNoticeList(vo);
		//System.out.println(studyNoticeList);
		
		model.addAttribute("studyNoticeList" , service.selectStudyNoticeList(vo));
		return "student/eclass/notice/eclassnotice";
	}
	
	
	
	// 학생 공지사항 상세조회
	@RequestMapping("/student/eclass/eclassnotice/{courseNoticeNo}")
	public String selectDetailStudyNotice(@PathVariable String courseNoticeNo, StudyNoticeVO vo, Model model) {
		
		// 해당 공지사항글 번호
		vo.setCourseCode("SSPY0001");
		vo.setCourseNoticeNo(courseNoticeNo);
		
		model.addAttribute("selectDetailStudyNotice", service.selectDetailStudyNotice(vo));
		return "student/eclass/notice/eclassdetailnotice";
				
	}
	
	
	// 교수 강의 공지사항 조회
	@RequestMapping("/professor/eclass/eclassnotice")
	public String selectProStudyNoticeList(@ModelAttribute("search") StudyNoticeVO vo, Model model) {
		vo.setCourseCode("SSPY0001");
		
		model.addAttribute("studyNoticeList" , service.selectProStudyNoticeList(vo));
		
		return "professor/eclass/notice/eclassnotice";
	}
	
	// 교수 공지사항 상세조회
	@RequestMapping("/professor/eclass/eclassnotice/{courseNoticeNo}")
	public String selectProDetailStudyNotice(@PathVariable String courseNoticeNo, StudyNoticeVO vo, Model model) {
		
		// 해당 공지사항글 번호
		vo.setCourseCode("SSPY0001");
		vo.setCourseNoticeNo(courseNoticeNo);
		
		model.addAttribute("selectProDetailStudyNotice", service.selectProDetailStudyNotice(vo));
		return "professor/eclass/notice/eclassdetailnotice";
				
	}
	
	// 교수 공지사항 등록 페이지
	@RequestMapping("/professor/eclass/eclassnoticewrite")
	public String StudyNoticeRegisterPage(StudyNoticeVO vo, Model model) {
		vo.setCourseCode("SSPY0001");
		model.addAttribute("StudyNoticeRegisterPage", service.insertStudyNoticePage(vo));
		return "professor/eclass/notice/eclassnoticewrite";
	}
	
	// 교수 공지사항 등록 처리
	@PostMapping("/professor/eclass/eclassnoticeinsert")
	public String insertStudyNotice(StudyNoticeVO vo, Model model) {
		
		vo.setCourseCode("SSPY0001");
		service.insertStudyNotice(vo);
		
		
		return "redirect:/professor/eclass/eclassnotice";
	}
	
	//교수 공지사항 삭제 처리
	@RequestMapping(value = "/professor/eclass/eclassnoticedelete", method = { RequestMethod.POST })
	//@PostMapping("/professor/eclass/eclassnoticedelete")
	public String deleteStudyNotice(StudyNoticeVO vo) {
		service.deleteStudyNotice(vo);
		
		return "redirect:/professor/eclass/eclassnotice";
	}
	
}
