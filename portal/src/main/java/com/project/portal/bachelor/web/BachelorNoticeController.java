package com.project.portal.bachelor.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.portal.bachelor.service.BachelorNoticeFileVO;
import com.project.portal.bachelor.service.BachelorNoticeService;
import com.project.portal.bachelor.service.BachelorNoticeVO;
import com.project.portal.common.Criteria;
import com.project.portal.common.PageDTO;
import com.project.portal.common.service.CodeService;
import com.project.portal.common.service.CodeVO;

@Controller
public class BachelorNoticeController {

	@Autowired
	BachelorNoticeService service;
	@Autowired
	CodeService codeService;

	@ModelAttribute("codeMap")
	public Map<String, List<CodeVO>> getCodeList() {
		String[] codes = { "O01", "S01" };
		return codeService.getAllDetailList(codes);
	}

	// 파일 업로드하는 상위 디렉토리
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;

	@RequestMapping({ "/student/notice", "professor/notice", "/admin/notice" })
	public String getNotice(HttpServletRequest request, 
							Model model, 
							Criteria cri) {
		String requestURI = request.getRequestURI();
		int command = setCommand(requestURI);
		BachelorNoticeVO vo = new BachelorNoticeVO();
		if (command == 1) {
			vo.setNoticeDivision("PROF");
			vo.setNoticePrivate("OP");
		} else if (command == 2) {
			vo.setNoticeDivision("STUD");
			vo.setNoticePrivate("OP");
		}
		model.addAttribute("noticeList", service.getNoticeList(vo, cri));
		model.addAttribute("pageMaker", new PageDTO(service.getTotal(vo, cri), cri.getAmount(), cri));
		model.addAttribute("command", command);
		return "common/notice";
	}

	@RequestMapping({ "/student/detailNotice/{noticeNo}", "/professor/detailNotice/{noticeNo}",
			"/admin/detailNotice/{noticeNo}" })
	public String detailNotice(@PathVariable String noticeNo, HttpServletRequest request, Model model,
			BachelorNoticeVO vo) {
		String requestURI = request.getRequestURI();
		int command = setCommand(requestURI);
		model.addAttribute("command", command);
		vo.setNoticeNo(noticeNo);
		service.hitIncrease(vo);
		model.addAttribute("notice", service.getNoticeList(vo, null).get(0));
		return "common/detailNotice";
	}

	@RequestMapping("/admin/insertNotice")
	public String insertNoticePage() {
		return "common/insertNotice";
	}

	@PostMapping("/admin/insertNotice")
	public String insertNotice(@RequestParam("file") MultipartFile file, BachelorNoticeVO vo) {
		// 파일 테이블에 등록하기
		if (file != null && !file.isEmpty()) {
			BachelorNoticeFileVO newFile = new BachelorNoticeFileVO();
			newFile.setNoticeFileName(file.getOriginalFilename());
			newFile.setNoticeFileExtension(file.getContentType());
			newFile.setNoticeFileStoredName(
					UUID.randomUUID().toString().replaceAll("-", "") + file.getOriginalFilename());
			newFile.setNoticeFilePath(uploadPath + "/notice/" + newFile.getNoticeFileStoredName());
			vo.setNoticeFile(newFile);

			// 비디오 실제 파일 업로드
			File fileUpload = new File(newFile.getNoticeFilePath());
			fileUpload.getParentFile().mkdirs();
			try {
				file.transferTo(fileUpload);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		service.insertNotice(vo);
		return "redirect:/admin/notice";
	}

	@RequestMapping("/admin/updateNotice/{noticeNo}")
	public String updateNoticePage(@PathVariable String noticeNo, BachelorNoticeVO vo, Model model) {
		vo.setNoticeNo(noticeNo);
		model.addAttribute("notice", service.getNoticeList(vo, null).get(0));
		return "common/updateNotice";
	}

	@PostMapping("/admin/updateNotice")
	public String updateNotice(@RequestParam("file") MultipartFile file, BachelorNoticeVO vo) {
		// 파일 테이블에 등록하기
		if (file != null && !file.isEmpty()) {
			BachelorNoticeFileVO newFile = new BachelorNoticeFileVO();
			newFile.setNoticeFileName(file.getOriginalFilename());
			newFile.setNoticeFileExtension(file.getContentType());
			newFile.setNoticeFileStoredName(
					UUID.randomUUID().toString().replaceAll("-", "") + file.getOriginalFilename());
			newFile.setNoticeFilePath(uploadPath + "/notice/" + newFile.getNoticeFileStoredName());
			service.updateNotice(vo, newFile);
			// 비디오 실제 파일 업로드
			File fileUpload = new File(newFile.getNoticeFilePath());
			fileUpload.getParentFile().mkdirs();
			try {
				file.transferTo(fileUpload);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		} else {
			service.updateNotice(vo, null);
		}
		return "redirect:/admin/detailNotice/" + vo.getNoticeNo();
	}

	@DeleteMapping("/admin/deleteNotice")
	@ResponseBody
	public String deleteNotice(BachelorNoticeVO vo) {
		service.deleteNotice(vo);
		return "success";
	}

	private int setCommand(String requestURI) {
		int command = 0;
		if (requestURI.startsWith("/professor/")) {
			command = 2;
		} else if (requestURI.startsWith("/student/")) {
			command = 1;
		} else if (requestURI.startsWith("/admin/")) {
			command = 3;
		}
		return command;
	}

}
