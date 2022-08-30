package com.project.portal.studynotice.web;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.portal.studynotice.service.StudyNoticeFileVO;
import com.project.portal.studynotice.service.StudyNoticeService;
import com.project.portal.studynotice.service.StudyNoticeVO;
import com.project.portal.tempcourse.web.TempcourseController;

@Controller
public class StudyNoticeController {
	private static final Logger logger = LoggerFactory.getLogger(TempcourseController.class);

	@Autowired
	StudyNoticeService service;

	// 학생 강의 공지사항 조회
	@RequestMapping("/student/eclass/eclassnotice")
	public String selectStudyNoticeList(@ModelAttribute("search") StudyNoticeVO vo, Model model) {

		vo.setCourseCode("SSPY0001");
		// List<StudyNoticeVO> studyNoticeList = service.selectStudyNoticeList(vo);
		// System.out.println(studyNoticeList);

		model.addAttribute("studyNoticeList", service.selectStudyNoticeList(vo));
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

		model.addAttribute("studyNoticeList", service.selectProStudyNoticeList(vo));

		return "professor/eclass/notice/eclassnotice";
	}

	// 교수 공지사항 상세조회
	@RequestMapping("/professor/eclass/eclassnotice/{courseNoticeNo}")
	public String selectProDetailStudyNotice(@PathVariable String courseNoticeNo,StudyNoticeFileVO filevo, StudyNoticeVO vo, Model model) {

		// 해당 공지사항글 번호
		vo.setCourseCode("SSPY0001");
		vo.setCourseNoticeNo(courseNoticeNo);
		
		model.addAttribute("selectFile" , service.selectFile(vo));
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
	public String insertStudyNotice(@RequestParam(name="courseNoticeAttachedFileName") MultipartFile[] files ,StudyNoticeVO vo, StudyNoticeFileVO filevo, Model model) throws IllegalStateException, IOException {
		vo.setCourseCode("SSPY0001");
		
		String groupNum = service.fileUploadGroupNum();
		for( MultipartFile file : files) {		
		// 업로드 파일이 없을 때
		    if(!file.isEmpty()) {
			// 원본 파일명 추출
			String fileOriName = file.getOriginalFilename();
			// 확장자 추출
			String fileNameExtension = fileOriName.substring(fileOriName.lastIndexOf("."));
			// 랜덤 파일명 값 생성
			String fileName = UUID.randomUUID().toString().replace("-","") + fileNameExtension;
			// 파일 URL
			String fileUrl = "C:\\file\\";
			
			// 파일객체생성
			File uploadfile = new File(fileUrl + fileName);
			
			// 파일을 경로에 저장
			file.transferTo(uploadfile);
			filevo.setFileName(fileName);
			filevo.setFileOriName(fileOriName);
			filevo.setGroupNo(groupNum);
			service.fileUpload(filevo); // 그룹번호 생성되면 
		}
		
		
		}
		vo.setCourseNoticeAttachedFile(groupNum);
		service.insertStudyNotice(vo);
		return "redirect:/professor/eclass/eclassnotice";
	}

	// 교수 공지사항 삭제 처리
	@RequestMapping(value = "/professor/eclass/eclassnoticedelete", method = { RequestMethod.POST })
	@ResponseBody
	// @PostMapping("/professor/eclass/eclassnoticedelete")
	public String deleteStudyNotice(StudyNoticeVO vo) {
		service.deleteStudyNotice(vo);
		return "success";
	}
	
	
	// 교수 공지사항 수정 처리
	@RequestMapping(value = "/professor/eclass/eclassnoticemodify", method = { RequestMethod.POST})
	public String modifyStudyNotice(StudyNoticeVO vo , HttpSession session) {
		//String courseCode = (String)session.getAttribute("courseCode");
		vo.setCourseCode("SSPY0001");
		service.modifyStudyNotice(vo);
		return "success";
		
	}
	


}
