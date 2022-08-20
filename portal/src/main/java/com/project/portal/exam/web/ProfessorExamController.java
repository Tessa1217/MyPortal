package com.project.portal.exam.web;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.portal.course.service.CourseVO;
import com.project.portal.course.service.impl.CourseServiceImpl;
import com.project.portal.exam.service.CourseExamVO;
import com.project.portal.exam.service.ExamInfoVO;
import com.project.portal.exam.service.ExamVO;
import com.project.portal.exam.service.impl.ProfessorExamServiceImpl;

@Controller
public class ProfessorExamController {
	
	@Autowired CourseServiceImpl courseService;
	@Autowired ProfessorExamServiceImpl examService;
	
	@RequestMapping("/professor/examCheck")
	public String examCheck() {
		return "professor/eclass/examCheck";
	}
	
	@RequestMapping("/professor/studentExamScore")
	public String studentExamScore() {
		return "professor/eclass/studentExamResult";
	}
	
	@RequestMapping("/professor/courseExamList")
	public String courseExamList() {
		return "professor/eclass/courseExamList";
		
	}
	
	@RequestMapping("/professor/examInsert")
	public String examInsert(CourseVO vo, Model model) {
		vo.setCourseCode("SSPY0001");
		// 주차 정보
		vo = courseService.getWeeklyInfo(vo);
		model.addAttribute("courseInfo", vo);
		// 시험 정보
		List<ExamInfoVO> examList = examService.getExamList(vo);
		model.addAttribute("examList", examList);
		// 시험지 정보
		List<CourseExamVO> courseExamInfo = examService.getCourseExam(examList);
		System.out.println(courseExamInfo);
		model.addAttribute("courseExam", courseExamInfo);
		return "professor/eclass/examInsert";
	}
	
	@PostMapping("/professor/examInsert")
	@ResponseBody
	public HashMap<String, Object> getExamList(CourseVO vo, RedirectAttributes re) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<ExamInfoVO> examList = examService.getExamList(vo);
		map.put("examInfo", examList);
		map.put("courseExam", examService.getCourseExam(examList));
		re.addAttribute("map", map);
		return map;
	}
	
	@PostMapping("/professor/generateTestPaper")
	public String generateTestPaper(ExamInfoVO vo, ExamVO examVO, Model model) {
		System.out.println(vo);
		System.out.println(examVO);
		model.addAttribute("exam", examVO);
		return "professor/eclass/examTestPaper";
	}
	
}
