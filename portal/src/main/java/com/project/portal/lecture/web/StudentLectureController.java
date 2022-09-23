package com.project.portal.lecture.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.portal.course.service.CourseVO;
import com.project.portal.course.service.impl.CourseServiceImpl;
import com.project.portal.lecture.service.LectureQuestionVO;
import com.project.portal.lecture.service.LectureVO;
import com.project.portal.lecture.service.StudentLectureVO;
import com.project.portal.lecture.service.StudentNoteVO;
import com.project.portal.lecture.service.impl.StudentLectureServiceImpl;

@Controller
public class StudentLectureController {
	
	@Autowired StudentLectureServiceImpl service;
	@Autowired CourseServiceImpl courseService;
	
	@ModelAttribute("courseInfo")
	public CourseVO course(HttpSession session) {
		CourseVO course = new CourseVO();
		course.setCourseCode((String) session.getAttribute("courseCode"));
		return courseService.getWeeklyInfo(course);
	}
	
	
	@RequestMapping("student/eclass/lectureList")
	public String lectureList(CourseVO vo, 
							StudentLectureVO slecture, 
							Model model,
							HttpSession session) {
		vo = (CourseVO) model.getAttribute("courseInfo");
		slecture.setStudentId((int) session.getAttribute("id"));
		model.addAttribute("record", service.getLectureRecord(slecture));
		model.addAttribute("lectureList", service.getLectureList(vo, null));
		
		return "student/eclass/lecture/lectureList";
	}
	
	@RequestMapping("student/eclass/watchLecture")
	public String watchLecture(LectureVO vo, 
								StudentNoteVO note, 
								Map<String, Object> map, 
								Model model,
								HttpSession session) {
		List<LectureVO> lecture = service.getLectureList(null, vo);
		note.setLectureCode(lecture.get(0).getLectureCode());
		note.setStudentId((int) session.getAttribute("id"));
		map = service.getList(lecture.get(0), note.getStudentId());
		model.addAttribute("lecture", lecture.get(0));
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
	
	@DeleteMapping("student/eclass/deleteNote")
	@ResponseBody
	public String deleteLectureNote(@RequestBody StudentNoteVO vo) {
		service.deleteStudentNote(vo);
		return "success";
	}
	
	@PostMapping("student/eclass/insertQuestion")
	@ResponseBody
	public LectureQuestionVO insertLectureQuestion(LectureQuestionVO vo) {
		vo = service.insertLectureQuestion(vo);
		return vo;
	}
	
	@RequestMapping("student/eclass/myNotes")
	public String getMyNotes(Model model, HttpSession session) {
		Map<String, List<StudentNoteVO>> map = new HashMap<String, List<StudentNoteVO>>();
		List<LectureVO> lectures = service.getLectureList((CourseVO) model.getAttribute("courseInfo"), null);
		for (LectureVO lecture : lectures) {
			List<StudentNoteVO> noteList = service.getNoteList(lecture, (int) session.getAttribute("id"));
			if (noteList.size() != 0) {
				map.put(lecture.getLectureCode(), noteList);
			}
		}
		model.addAttribute("lectureList", lectures);
		model.addAttribute("map", map);
		return "student/eclass/lecture/lectureNoteList";
	}
}
