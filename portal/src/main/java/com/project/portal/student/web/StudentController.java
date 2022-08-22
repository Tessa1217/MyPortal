package com.project.portal.student.web;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.portal.common.Criteria;
import com.project.portal.student.service.StudentService;
import com.project.portal.student.service.StudentVO;
import com.project.portal.tempcourse.web.TempcourseController;

@Controller
public class StudentController {

	private static final Logger logger = LoggerFactory.getLogger(TempcourseController.class);
	
	@Autowired StudentService service;
	
	//학생 전체 조회(관리자)
	@RequestMapping("/admin/studentList")
	public String Student(StudentVO vo, Model model, Criteria cri) {
		model.addAttribute("studentList", service.studentList(cri));
		return "admin/info/studentList";
	}
	//학생 개인 조회(학생)
	@RequestMapping("/student/studentInfoSelect")
	public String Student(StudentVO vo, Model model) {
		model.addAttribute("studentInfoSelect", service.studentInfoSelect(vo));
		return "student/info/personal";
	}
	////////
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String fileName) {
		Resource resource = new FileSystemResource("c:\\faces\\" + fileName);
		if (resource.exists() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		String resourceName = resource.getFilename();
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			String downloadName = null;
			if(userAgent.contains("Trident")) {
				logger.info("IE browser");
			}
			headers.add("Content-Disposition", "attachment; filename=" + downloadName);
		} catch (UnsupportedEncodingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
		
	}
}
