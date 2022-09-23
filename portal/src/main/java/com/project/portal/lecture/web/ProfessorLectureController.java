package com.project.portal.lecture.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.portal.common.Criteria;
import com.project.portal.common.PageDTO;
import com.project.portal.course.service.CourseVO;
import com.project.portal.course.service.impl.CourseServiceImpl;
import com.project.portal.lecture.service.LectureVO;
import com.project.portal.lecture.service.VideoVO;
import com.project.portal.lecture.service.impl.ProfessorLectureServiceImpl;

// 작성자: 권유진 

@Controller
@RequestMapping("/professor/eclass")
public class ProfessorLectureController {

	Logger log = LoggerFactory.getLogger(ProfessorLectureController.class);

	@Autowired
	CourseServiceImpl courseService;
	@Autowired
	ProfessorLectureServiceImpl service;

	// 파일 업로드하는 상위 디렉토리
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;

	// 강의 리스트 페이지 이동
	@RequestMapping("/lectureList")
	public String lectureList(Model model, Criteria cri, HttpSession session) {
		CourseVO course = (CourseVO) session.getAttribute("courseInfo");
		List<LectureVO> lectureList = service.getLectureList(course, null, cri);
		model.addAttribute("lectureList", lectureList);
		model.addAttribute("pageMaker", new PageDTO(service.getLectureTotal(course), cri.getAmount(), cri));
		return "professor/eclass/lecture/lectureList";
	}

	// 강의 등록 페이지
	@RequestMapping("/insertLecture")
	public String insertLecture(CourseVO vo, Model model) {
		return "professor/eclass/lecture/insertLecture";
	}

	// 강의 등록
	@PostMapping("/insertLecture")
	public String loadedData(LectureVO lecture, MultipartFile file, VideoVO video, Model model, HttpSession session)
			throws IllegalStateException, IOException {
		if (!file.isEmpty()) {
			// 비디오 파일 테이블에 등록하기
			CourseVO course = (CourseVO) session.getAttribute("courseInfo");
			video.setVideoName(file.getOriginalFilename());
			video.setVideoStoredName(UUID.randomUUID().toString().replaceAll("-", "") + file.getOriginalFilename());
			video.setVideoExtension(file.getContentType());
			video.setVideoFilePath(uploadPath + "/video/" + course.getCourseYear() + "/" + course.getCourseSemester()
					+ "/" + course.getCourseCode() + "/" + video.getVideoStoredName());
			video.setProfessorId(course.getProfessorId());
			service.uploadVideo(video);

			// 비디오 실제 파일 업로드
			File fileUpload = new File(video.getVideoFilePath());

			// 부모 디렉토리가 존재하지 않을 경우 생성하여 오류 막는 부분
			fileUpload.getParentFile().mkdirs();
			file.transferTo(fileUpload);

			// 비디오 코드 받아서 강의 영상 자료 정보 테이블에 등록해오기
			lecture.setVideoCode(video.getVideoCode());
		}
		service.insertLecture(lecture);

		return "redirect:/professor/eclass/lectureList";
	}

	// 강의 상세보기 & 수정하기 페이지 이동
	@RequestMapping("/detailLecture/{lectureCode}")
	public String detailLecture(@PathVariable String lectureCode, CourseVO course, Model model, HttpSession session) {
		course = (CourseVO) session.getAttribute("courseInfo");
		LectureVO vo = new LectureVO();
		vo.setLectureCode(lectureCode);
		List<LectureVO> lecture = service.getLectureList(course, vo, null);
		model.addAttribute("lecture", lecture.get(0));
		return "professor/eclass/lecture/detailLecture";
	}

	// 강의 삭제
	@DeleteMapping("/deleteLecture")
	@ResponseBody
	public String deleteLecture(@RequestBody LectureVO vo) {
		service.deleteLecture(vo);
		return "success";
	}

	// 강의 수정
	@PostMapping("/updateLecture")
	public String updateLecture(@RequestParam("file") MultipartFile file, LectureVO vo, HttpSession session,
			Model model) {
		if (!file.isEmpty()) {
			if (vo.getVideoCode() != null) {
				File storedFile = new File(service.getVideo(vo.getVideoCode()).getVideoFilePath());
				if (storedFile.exists()) {
					storedFile.delete();
				}
			}
			CourseVO course = (CourseVO) session.getAttribute("courseInfo");
			VideoVO newFile = newFile(file, course, session);
			service.updateLecture(vo, newFile);
		} else {
			service.updateLectureOnly(vo);
		}
		return "redirect:/professor/eclass/lectureList";
	}

	@RequestMapping("/videoList")
	public String getVideoList(CourseVO course, Criteria cri, HttpSession session, Model model) {
		course.setProfessorId((int) session.getAttribute("id"));
		model.addAttribute("fileList", service.getVideoList(course, cri));
		model.addAttribute("pageMaker", new PageDTO(service.getVideoTotal(course, cri), cri.getAmount(), cri));
		return "layout/fragments/professor-eclass/lecture/fileList :: #tableFragment";
	}

	// 파일 세팅하는 메서드
	private VideoVO newFile(MultipartFile file, CourseVO course, HttpSession session) {
		VideoVO newFile = new VideoVO();
		newFile.setProfessorId((int) session.getAttribute("id"));
		newFile.setVideoExtension(file.getContentType());
		newFile.setVideoName(file.getOriginalFilename());
		newFile.setVideoStoredName(UUID.randomUUID().toString().replaceAll("-", "") + file.getOriginalFilename());
		newFile.setVideoFilePath(uploadPath + "/video/" + course.getCourseYear() + "/" + course.getCourseSemester()
				+ "/" + course.getCourseCode() + "/" + newFile.getVideoStoredName());
		return newFile;
	}

}
