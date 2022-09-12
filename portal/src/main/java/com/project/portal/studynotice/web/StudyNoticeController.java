package com.project.portal.studynotice.web;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.project.portal.common.Criteria;
import com.project.portal.common.PageDTO;
import com.project.portal.course.service.CourseVO;
import com.project.portal.course.service.impl.CourseServiceImpl;
import com.project.portal.studynotice.service.StudyNoticeFileVO;
import com.project.portal.studynotice.service.StudyNoticeService;
import com.project.portal.studynotice.service.StudyNoticeVO;
import com.project.portal.tempcourse.web.TempcourseController;


@Controller
public class StudyNoticeController {
	private static final Logger logger = LoggerFactory.getLogger(TempcourseController.class);

	@Autowired
	StudyNoticeService service;
	
	@Autowired CourseServiceImpl courseService;
	
	@ModelAttribute("courseInfo")
	public CourseVO course(HttpSession session) {
		CourseVO course = new CourseVO();
		course.setCourseCode((String) session.getAttribute("courseCode"));
		return courseService.getWeeklyInfo(course);
	}
	
	
	
	
	

	// 학생 강의 공지사항 조회
	@RequestMapping("/student/eclass/eclassnotice")
	public String selectStudyNoticeList(StudyNoticeVO vo, Criteria cri, Model model, HttpSession session) {
		
		vo.setCourseCode((String)session.getAttribute("courseCode"));
		
		model.addAttribute("pageMaker", new PageDTO(service.getTotal(vo), cri.getAmount(), cri));
		model.addAttribute("studyNoticeList", service.selectStudyNoticeList(vo, cri));
		return "student/eclass/notice/eclassnotice";
	}

	// 학생 공지사항 상세조회
	@RequestMapping("/student/eclass/eclassnotice/{courseNoticeNo}")
	public String selectDetailStudyNotice(@PathVariable String courseNoticeNo, StudyNoticeFileVO filevo, StudyNoticeVO vo, Model model, HttpSession session) {

		// 해당 공지사항글 번호
		vo.setCourseCode((String)session.getAttribute("courseCode"));
		vo.setCourseNoticeNo(courseNoticeNo);
		service.updateHit(vo.getCourseNoticeNo());
		model.addAttribute("selectFile" , service.selectFile(vo));
		model.addAttribute("selectDetailStudyNotice", service.selectDetailStudyNotice(vo));
		return "student/eclass/notice/eclassdetailnotice";

	}

	// 교수 강의 공지사항 조회
	@RequestMapping("/professor/eclass/eclassnotice")
	public String selectProStudyNoticeList(StudyNoticeVO vo, Criteria cri, Model model, HttpSession session) {
		vo.setCourseCode((String)session.getAttribute("courseCode"));

		model.addAttribute("studyNoticeList", service.selectProStudyNoticeList(vo, cri));
		model.addAttribute("pageMaker", new PageDTO(service.getTotal(vo), cri.getAmount(), cri));
		return "professor/eclass/notice/eclassnotice";
	}

	// 교수 공지사항 상세조회
	@RequestMapping("/professor/eclass/eclassnotice/{courseNoticeNo}")
	public String selectProDetailStudyNotice(@PathVariable String courseNoticeNo,StudyNoticeFileVO filevo, StudyNoticeVO vo, Model model, HttpSession session) {

		// 해당 공지사항글 번호
		vo.setCourseCode((String)session.getAttribute("courseCode"));
		vo.setCourseNoticeNo(courseNoticeNo);
		
		service.updateHit(vo.getCourseNoticeNo());
		model.addAttribute("selectFile" , service.selectFile(vo));
		model.addAttribute("selectProDetailStudyNotice", service.selectProDetailStudyNotice(vo));
		return "professor/eclass/notice/eclassdetailnotice";

	}

	// 교수 공지사항 등록 페이지
	@RequestMapping("/professor/eclass/eclassnoticewrite")
	public String StudyNoticeRegisterPage(StudyNoticeVO vo, Model model , HttpSession session) {
		vo.setCourseCode((String)session.getAttribute("courseCode"));
		model.addAttribute("StudyNoticeRegisterPage", service.insertStudyNoticePage(vo));
		return "professor/eclass/notice/eclassnoticewrite";
	}
	
	// 파일 다운로드 경로 가져오기
	@Value("${spring.servlet.multipart.location}")
    private String filelocation;

	// 교수 공지사항 등록 처리
	@PostMapping("/professor/eclass/eclassnoticeinsert")
	public String insertStudyNotice(@RequestParam(name="courseNoticeAttachedFileName") MultipartFile[] files , StudyNoticeVO vo, StudyNoticeFileVO filevo, Model model, HttpSession session) throws IllegalStateException, IOException {
		vo.setCourseCode((String)session.getAttribute("courseCode"));
		
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
			// 파일 URL (년도랑 학기 넣어주세요~)
			String fileUrl = filelocation + "/courseNotice/" + vo.getCourseCode() + "/" + fileName;
			// 파일객체생성
			File uploadfile = new File(fileUrl);
			
			// 파일을 경로에 저장
			file.transferTo(uploadfile);
			// 파일 업로드 정보 insert
			filevo.setFileName(fileName);
			filevo.setFileOriName(fileOriName);
			filevo.setFileUrl(fileUrl);
			filevo.setGroupNo(groupNum);
			service.fileUpload(filevo); // 그룹번호 생성되면 
		}
		
		
		}
		// 그룹넘버 설정 및 게시글 insert
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
	
	
	
	
	// 교수 공지사항 수정페이지 이동
	@RequestMapping("/professor/eclass/eclassnotice/{courseNoticeNo}/eclassnoticemodify")
	public String modifyStudyNotice(@PathVariable String courseNoticeNo, StudyNoticeVO vo , HttpSession session,StudyNoticeFileVO filevo, Model model) {
		// 해당 공지사항글 번호
				vo.setCourseCode((String)session.getAttribute("courseCode"));
				vo.setCourseNoticeNo(courseNoticeNo);
				model.addAttribute("selectFile" , service.selectFile(vo));
				model.addAttribute("selectProDetailStudyNotice", service.selectProDetailStudyNotice(vo));
		return "professor/eclass/notice/eclassnoticemodify";
		
	}
	
	// 교수 공지사항 수정 처리
	@RequestMapping("/professor/eclass/eclassnoticemodify")
	public String modifyProfStudyNotice(StudyNoticeVO vo , HttpSession session,StudyNoticeFileVO filevo, Model model) {
		vo.setCourseCode((String)session.getAttribute("courseCode"));
		
		service.modifyProfStudyNotice(vo);		
		return "redirect:/professor/eclass/eclassnotice";
	}
	


}
