package com.project.portal.student.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.portal.common.PageDTO;
import com.project.portal.common.service.CodeVO;
import com.project.portal.common.service.impl.CodeServiceImpl;
import com.project.portal.student.service.StudentVO;
import com.project.portal.student.service.impl.StudentServiceImpl;
import com.project.portal.tempcourse.web.TempcourseController;

// 작성자: 김진형
@Controller
public class StudentController {

	private static final Logger logger = LoggerFactory.getLogger(TempcourseController.class);
	
	@Autowired StudentServiceImpl service;
	@Autowired CodeServiceImpl codeService;
	
	// 파일 다운로드 경로
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	//학생 전체 조회(관리자)
	@RequestMapping("/admin/studentList")
	public String StudentList(Model model, StudentVO vo) {
		model.addAttribute("studentList", service.studentList(vo));
		model.addAttribute("pageMaker", new PageDTO(service.getTotal(vo), vo.getAmount(), vo));
		return "admin/info/studentList";
	}
	
	//학생 상세조회(관리자)
		@RequestMapping("/admin/adminStudentInfoSelect")
		public String AdminStudentInfoSelect(StudentVO vo, Model model) {
			List<CodeVO> codeList = new ArrayList<CodeVO>();
			codeList = codeService.getDetailList("S03");
			model.addAttribute("adminStudentInfoSelect", service.adminStudentInfoSelect(vo));
			model.addAttribute("codeList", codeList);
			return "admin/info/studentPersonal";
		}
	//학생 정보 수정(관리자)
	@RequestMapping("/admin/studentInfoUpdate")
	@ResponseBody
	public StudentVO AdminStudentInfoUpdate(StudentVO vo, Model model) {
		return service.adminStudentInfoUpdate(vo);
	}
	
	//학생 개인 조회(학생)
	@RequestMapping("/student/studentInfoSelect")
	public String StudentInfoSelect(StudentVO vo, Model model, HttpSession session) {
		// 부모 공통 코드
		List<CodeVO> codeList = new ArrayList<CodeVO>();
		vo.setStudentId((int) session.getAttribute("id"));
		codeList = codeService.getDetailList("R02");
		model.addAttribute("studentInfoSelect", service.studentInfoSelect(vo));
		model.addAttribute("codeList", codeList);
		return "student/info/personal";
	}
		
	//학생 정보 수정(학생)
	@RequestMapping("/student/studentInfoUpdate")
	@ResponseBody
	public StudentVO StudentInfoUpdate(StudentVO vo, 
										Model model,
										HttpSession session) {
		vo.setStudentId((int) session.getAttribute("id"));
		return service.studentInfoUpdate(vo);
	}

}
