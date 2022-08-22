package com.project.portal.studynotice.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.studynotice.service.StudyNoticeService;
import com.project.portal.studynotice.service.StudyNoticeVO;
import com.project.portal.tempcourse.web.TempcourseController;

@Controller
public class StudyNoticeController {
	private static final Logger logger = LoggerFactory.getLogger(TempcourseController.class);
	
	@Autowired StudyNoticeService service;
	
	// 학생 강의 공지사항 조회
	@RequestMapping("/student/eclass/eclassnotice")
	public String selectStudyNoticeList(StudyNoticeVO vo, Model model) {

		//List<StudyNoticeVO> studyNoticeList = service.selectStudyNoticeList(vo);
		//System.out.println(studyNoticeList);
		
		model.addAttribute("studyNoticeList" , service.selectStudyNoticeList(vo));
		return "student/eclass/notice/eclassnotice";
	}
	
	
	
	// 공지사항 상세조회
	@RequestMapping("/student/eclass/eclassnotice/{courseNoticeNo}")
	public String selectDetailStudyNotice(@PathVariable String courseNoticeNo, StudyNoticeVO vo, Model model) {
		
		// 해당 공지사항글 번호
		vo = service.selectDetailStudyNotice(courseNoticeNo);
		System.out.println(vo);
		
		model.addAttribute("selectDetailStudyNotice", service.selectDetailStudyNotice(courseNoticeNo));
		return "student/eclass/notice/eclassdetailnotice";
				
	}
}
