package com.project.portal.lecture.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.portal.course.service.CourseVO;
import com.project.portal.lecture.service.LectureQuestionVO;
import com.project.portal.lecture.service.LectureVO;
import com.project.portal.lecture.service.StudentLectureVO;
import com.project.portal.lecture.service.StudentNoteVO;
import com.project.portal.lecture.service.impl.StudentLectureServiceImpl;

@Controller
public class StudentLectureController {
	
	@Autowired StudentLectureServiceImpl service;
	
	@ModelAttribute("courseInfo")
	public CourseVO course(HttpSession session) {
		CourseVO course = new CourseVO();
		course.setCourseCode((String) session.getAttribute("courseCode"));
		System.out.println(course);
		return course;
	}
	
	
	@RequestMapping("student/eclass/lectureList")
	public String lectureList(CourseVO vo, StudentLectureVO slecture, Model model) {
		vo.setCourseCode("SSPY0001");
		slecture.setStudentId(22000001);
		model.addAttribute("record", service.getLectureRecord(slecture));
		model.addAttribute("lectureList", service.getLectureList(vo));
		
		return "student/eclass/lecture/lectureList";
	}
	
	@RequestMapping("student/eclass/watchLecture")
	public String watchLecture(LectureVO vo, StudentNoteVO note, Map<String, Object> map, Model model) {
		vo = service.getLecture(vo);
		note.setLectureCode(vo.getLectureCode());
		note.setStudentId(22000001);
		map = service.getList(vo, note.getStudentId());
		model.addAttribute("lecture", vo);
		model.addAttribute("noteList", map.get("noteList"));
		model.addAttribute("questionList", map.get("questionList"));
		return "student/eclass/lecture/watchLecture";
	}
	
	@PostMapping("student/eclass/watchLecture")
	@ResponseBody
	public String watchRecord(@RequestBody StudentLectureVO vo) {
		if (vo.getCmd().equals("insert")) {
			service.insertLectureRecord(vo);
		} 
		if (vo.getCmd().equals("update")) {
			service.updateLectureRecord(vo);
		}
		return "success";
	}
	
	@PostMapping("student/eclass/insertNote")
	@ResponseBody
	public StudentNoteVO insertLectureNote(StudentNoteVO vo) {
		vo = service.insertStudentNote(vo);
		return vo;
	}
	
	@PostMapping("student/eclass/insertQuestion")
	@ResponseBody
	public LectureQuestionVO insertLectureQuestion(LectureQuestionVO vo) {
		System.out.println(vo);
		vo = service.insertLectureQuestion(vo);
		return vo;
	}
	
	@RequestMapping("student/eclass/myNotes")
	public String getMyNotes(Model model) {
		Map<String, List<StudentNoteVO>> map = new HashMap<String, List<StudentNoteVO>>();
		List<LectureVO> lectures = service.getLectureList((CourseVO) model.getAttribute("courseInfo"));
		for (LectureVO lecture : lectures) {
			map.put(lecture.getLectureCode(), service.getNoteList(lecture, 22000001));
		}
		System.out.println(map);
		model.addAttribute("lectureList", lectures);
		model.addAttribute("map", map);
		return "student/eclass/lecture/lectureNoteList";
	}
}
