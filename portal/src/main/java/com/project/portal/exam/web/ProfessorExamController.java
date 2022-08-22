package com.project.portal.exam.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.portal.course.service.CourseVO;
import com.project.portal.course.service.impl.CourseServiceImpl;
import com.project.portal.exam.service.CourseExamVO;
import com.project.portal.exam.service.ExamInfoVO;
import com.project.portal.exam.service.ExamScoreVO;
import com.project.portal.exam.service.ExamVO;
import com.project.portal.exam.service.impl.ProfessorExamServiceImpl;

@Controller
public class ProfessorExamController {
	
	@Autowired CourseServiceImpl courseService;
	@Autowired ProfessorExamServiceImpl examService;
	
	@RequestMapping("/professor/eclass/examScore")
	public String studentExamScore(CourseVO vo, Model model) {
		return "professor/eclass/exam/examScore";
	}
	
	@PostMapping(value = "/professor/eclass/examScore", produces = "application/json")
	public @ResponseBody Object getExamScore(CourseVO vo, Model model) {
		vo.setCourseCode("SSPY0001");
		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put("data", examService.getExamScore(vo));
		Object result = mp;
		return result;
	}
	
	@RequestMapping("/professor/eclass/examList")
	public String courseExamList(CourseVO vo, Model model) {
		vo.setCourseCode("SSPY0001");
		List<ExamVO> examList = examService.getExamInfoList(vo);
		model.addAttribute("examList", examList);
		return "professor/eclass/exam/examList";
	}
	
	@RequestMapping("/professor/eclass/examInsert/{examCode}")
	public String examInsert(@PathVariable String examCode, ExamVO exam, CourseVO vo, Model model) {
		
		// 기존 시험 정보
		exam.setExamCode(examCode);
		exam = examService.getExam(exam);
		model.addAttribute("exam", exam);
		System.out.println(exam);
		vo.setCourseCode("SSPY0001");
		// 주차 정보
		vo = courseService.getWeeklyInfo(vo);
		model.addAttribute("courseInfo", vo);
		// 시험 정보
		List<ExamInfoVO> examList = examService.getExamList(vo);
		model.addAttribute("examList", examList);
		// 시험지 정보
		List<CourseExamVO> courseExamInfo = examService.getCourseExam(examList);
		model.addAttribute("courseExam", courseExamInfo);
		model.addAttribute("command", "2");
		return "professor/eclass/exam/examInsert";
	}
	
	@GetMapping("/professor/eclass/examInsert")
	public String newExamInsert(CourseVO vo, Model model) {
		vo.setCourseCode("SSPY0001");
		// 주차 정보
		vo = courseService.getWeeklyInfo(vo);
		model.addAttribute("courseInfo", vo);
		// 시험 정보
		List<ExamInfoVO> examList = examService.getExamList(vo);
		model.addAttribute("examList", examList);
		// 시험지 정보
		List<CourseExamVO> courseExamInfo = examService.getCourseExam(examList);
		model.addAttribute("courseExam", courseExamInfo);
		model.addAttribute("command", "1");
		return "professor/eclass/exam/examInsert";
	}
	
	@PostMapping("/professor/eclass/examInsert")
	@ResponseBody
	public String getExamList(CourseVO course, ExamVO exam) {
		examService.insertExam(course, exam);
		return "success";
	}
	
	@PostMapping("/professor/eclass/generateTestPaper")
	public String generateTestPaper(ExamInfoVO vo, ExamVO examVO, Model model) {
		System.out.println(vo);
		System.out.println(examVO);
		model.addAttribute("exam", examVO);
		return "professor/eclass/exam/examTestPaper";
	}
	
}
