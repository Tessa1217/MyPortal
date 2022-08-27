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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.portal.course.service.CourseVO;
import com.project.portal.course.service.impl.CourseServiceImpl;
import com.project.portal.exam.service.CourseExamVO;
import com.project.portal.exam.service.ExamFilterVO;
import com.project.portal.exam.service.ExamInfoVO;
import com.project.portal.exam.service.ExamVO;
import com.project.portal.exam.service.QuestionVO;
import com.project.portal.exam.service.SaveCourseExamVO;
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
		List<ExamVO> examList = examService.getExamList((CourseVO) model.getAttribute("courseInfo"), null);
		model.addAttribute("examList", examList);
		return "professor/eclass/exam/examList";
	}
	
	@RequestMapping("/professor/eclass/examInsert/{examCode}")
	public String examInsert(@PathVariable String examCode, ExamVO exam, ExamInfoVO vo, Model model) {
		// 기존 시험 정보
		exam.setExamCode(examCode);
		List<ExamVO> examList = examService.getExamList((CourseVO) model.getAttribute("courseInfo"), exam);
		model.addAttribute("examList", examList);
		model.addAttribute("command", "2");
		vo.setExamCode(examCode);
		List<CourseExamVO> courseExamInfo = examService.getCourseExam(vo, null);
		model.addAttribute("examQuestions", courseExamInfo);
		return "professor/eclass/exam/examInsert";
	}
	
	@PostMapping("/professor/eclass/prevExamList")
	public String prevExamList(ExamInfoVO vo, Model model) {
		List<ExamInfoVO> examList = examService.getExamInfoList(vo);
		model.addAttribute("examList", examList);
		return "layout/fragments/professor-eclass/exam/prevExamList :: #prevExamList";
	}
	
	@PostMapping("/professor/eclass/getCourseExam")
	public String getCourseExam(@RequestBody ExamFilterVO vo, Model model) {
		List<CourseExamVO> courseExamInfo = examService.getCourseExam(vo.getExam(), vo.getFilterQuestions());
		model.addAttribute("courseExam", courseExamInfo);
		return "layout/fragments/professor-eclass/exam/prevExamList :: #testQuestions";
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
	
	@PostMapping("/professor/eclass/createQuestion")
	public String createQuestion(@RequestBody QuestionVO vo, Model model) {
		examService.createQuestion(vo);
		System.out.println(vo);
		model.addAttribute("question", vo);
		return "/layout/fragments/professor-eclass/exam/newTestQuestion :: #newQuestion";
	}
	
	@PostMapping("/professor/eclass/saveTest")
	@ResponseBody
	public String saveTestTemporary(@RequestBody SaveCourseExamVO vo) {
		examService.insertCourseExam(vo.getCourseExamList());
		if (vo.getCommand().equals("temp")) {
			return "success";
		}
		if (vo.getCommand().equals("submit")) {
			return "submitted";
		}
		return null;
	}
	
	@RequestMapping("/professor/eclass/generateTestPaper/{examCode}")
	public String generateTestPaper(@PathVariable String examCode, ExamInfoVO vo, ExamVO examVO, Model model) {
		vo.setExamCode(examCode);
		List<ExamInfoVO> examList = examService.getExamInfoList(vo);
		model.addAttribute("examList", examList);
		List<CourseExamVO> courseExamInfo = examService.getCourseExam(vo, null);
		model.addAttribute("courseExamList", courseExamInfo);
		return "professor/eclass/exam/examTestPaper";
	}
	
	
	@PostMapping("/professor/eclass/examSubmit")
	public String examInfoUpdate(ExamVO vo, Model model) {
		examService.updateExam(vo);
		List<ExamVO> examList = examService.getExamList((CourseVO) model.getAttribute("courseInfo"), vo);
		model.addAttribute("examList", examList);
		return "/layout/fragments/professor-eclass/exam/updateForm :: #update-container";
	}
	
	@DeleteMapping("/professor/eclass/examSubmit")
	@ResponseBody
	public String examInfoDelete(ExamVO vo, Model model) {
		examService.deleteExam(vo);
		return "success";
	}
	
	@PostMapping("/professor/eclass/finalSubmit")
	@ResponseBody
	public String finalSubmit(ExamInfoVO vo) {
		examService.finalExamSubmit(vo);
		return "completed";
	}

	
}
