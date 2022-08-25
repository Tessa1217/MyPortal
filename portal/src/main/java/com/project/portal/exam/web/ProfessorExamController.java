package com.project.portal.exam.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@ModelAttribute("courseInfo")
	public CourseVO course(HttpSession session) {
		CourseVO course = new CourseVO();
		course.setCourseCode((String) session.getAttribute("courseCode"));
		return courseService.getWeeklyInfo(course);
	}
	
	@RequestMapping("/professor/eclass/examScore")
	public String studentExamScore() {
		return "professor/eclass/exam/examScore";
	}
	
	@PostMapping(value = "/professor/eclass/examScore", produces = "application/json")
	public @ResponseBody Object getExamScore(Model model) {
		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put("data", examService.getExamScore((CourseVO) model.getAttribute("courseInfo")));
		Object result = mp;
		return result;
	}
	
	@RequestMapping("/professor/eclass/examList")
	public String courseExamList(Model model) {
		List<ExamVO> examList = examService.getExamInfoList((CourseVO) model.getAttribute("courseInfo"));
		model.addAttribute("examList", examList);
		return "professor/eclass/exam/examList";
	}
	
	@RequestMapping("/professor/eclass/examInsert/{examCode}")
	public String examInsert(@PathVariable String examCode, ExamVO exam, Model model) {
		// 기존 시험 정보
		exam.setExamCode(examCode);
		exam = examService.getExam(exam);
		model.addAttribute("exam", exam);
		model.addAttribute("command", "2");
		return "professor/eclass/exam/examInsert";
	}
	
	@PostMapping("/professor/eclass/prevExamList")
	public String prevExamList(ExamVO exam, Model model) {
		List<ExamInfoVO> examList = examService.getExamList((CourseVO) model.getAttribute("courseInfo"));
		model.addAttribute("examList", examList);
		return "layout/fragments/professor-eclass/exam/examList :: #prevExamList";
	}
	
	@PostMapping("/professor/eclass/getCourseExam")
	public String getCourseExam(ExamInfoVO vo, Model model) {
		List<CourseExamVO> courseExamInfo = examService.getCourseExam(vo);
		model.addAttribute("courseExam", courseExamInfo);
		return "layout/fragments/professor-eclass/exam/examList :: #testQuestions";
	}
	
	@GetMapping("/professor/eclass/examInsert")
	public String newExamInsert(Model model) {
		model.addAttribute("command", "1");
		System.out.println("코스 인포: " + model.getAttribute("courseInfo"));
		return "professor/eclass/exam/examInsert";
	}
	
	@PostMapping("/professor/eclass/examInsert")
	@ResponseBody
	public ExamVO getExamList(CourseVO course, ExamVO exam) {
		examService.insertExam(course, exam);
		return exam;
	}
	
	@PostMapping("/professor/eclass/generateTestPaper")
	public String generateTestPaper(ExamInfoVO vo, ExamVO examVO, Model model) {
		model.addAttribute("exam", examVO);
		return "professor/eclass/exam/examTestPaper";
	}
	
	
	@PostMapping("/professor/eclass/examSubmit")
	public String examInfoUpdate(ExamVO vo, Model model) {
		examService.updateExam(vo);
		vo = examService.getExam(vo);
		model.addAttribute("exam", vo);
		return "/layout/fragments/professor-eclass/exam/updateForm :: #update-container";
	}
	
	@DeleteMapping("/professor/eclass/examSubmit")
	@ResponseBody
	public String examInfoDelete(ExamVO vo, Model model) {
		examService.deleteExam(vo);
		return "success";
	}

	
}
