package com.project.portal.exam.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.portal.common.Criteria;
import com.project.portal.common.PageDTO;
import com.project.portal.common.service.CodeService;
import com.project.portal.course.service.CourseVO;
import com.project.portal.exam.service.CourseExamVO;
import com.project.portal.exam.service.ExamFilterVO;
import com.project.portal.exam.service.ExamInfoVO;
import com.project.portal.exam.service.ExamScoreVO;
import com.project.portal.exam.service.ExamVO;
import com.project.portal.exam.service.ProfessorExamService;
import com.project.portal.exam.service.QuestionVO;
import com.project.portal.exam.service.SaveCourseExamVO;
import com.project.portal.mycourse.service.MyCourseService;

// 작성자: 권유진
@Controller
@RequestMapping("/professor/eclass")
public class ProfessorExamController {
	
	@Autowired 
	MyCourseService mycourseService;
	@Autowired 
	ProfessorExamService examService;
	@Autowired
	CodeService codeService;
	
	// 강의 시험 리스트 페이지
	@RequestMapping("/examList")
	public String courseExamList(Model model, HttpSession session) {
		List<ExamVO> examList = examService.getExamList((CourseVO) session.getAttribute("courseInfo"), null);
		model.addAttribute("examList", examList);
		return "professor/eclass/exam/examList";
	}
	
	// 수강생 성적 조회
	@RequestMapping("/examScore")
	public String studentExamScore(HttpSession session, 
								Model model, 
								Criteria cri, 
								CourseVO course) {
		course = (CourseVO) session.getAttribute("courseInfo");
		List<ExamScoreVO> scores = examService.getExamScore(course, cri);
		model.addAttribute("scores", scores);
		model.addAttribute("pageMaker", new PageDTO(examService.getExamScoreTotal(course, cri), cri.getAmount(), cri));
		return "professor/eclass/exam/examScore";
	}
	
	// 새로운 시험 생성 페이지 이동
	@GetMapping("/examInsert")
	public String newExamInsert(Model model, HttpSession session, CourseVO vo) {
		vo = (CourseVO) session.getAttribute("courseInfo");
		model.addAttribute("testDates", examService.getTestDate(vo, "BPLAN04", "BPLAN05"));
		model.addAttribute("command", "1");
		return "professor/eclass/exam/examInsert";
	}
	
	// 새로운 시험 등록
	@PostMapping("/examInsert")
	@ResponseBody
	public ExamVO getExamList(CourseVO course, ExamVO exam) {
		examService.insertExam(course, exam);
		return exam;
	}
	
	// 기존에 있는 시험 페이지 이동
	@RequestMapping("/examInsert/{examCode}")
	public String examInsert(@PathVariable String examCode, 
							ExamVO exam, 
							ExamInfoVO vo, 
							Model model,
							HttpSession session) {
		// 기존 시험 정보
		exam.setExamCode(examCode);
		List<ExamVO> examList = examService.getExamList((CourseVO) session.getAttribute("courseInfo"), exam);
		model.addAttribute("examList", examList);
		model.addAttribute("command", "2");
		vo.setExamCode(examCode);
		List<CourseExamVO> courseExamInfo = examService.getCourseExam(vo, null);
		model.addAttribute("examQuestions", courseExamInfo);
		return "professor/eclass/exam/examInsert";
	}
	
	// 기존에 생성된 시험 정보 변경
	@PostMapping("/examSubmit")
	public String examInfoUpdate(ExamVO vo, Model model, HttpSession session) {
		examService.updateExam(vo);
		List<ExamVO> examList = examService.getExamList((CourseVO) session.getAttribute("courseInfo"), vo);
		model.addAttribute("examList", examList);
		return "layout/fragments/professor-eclass/exam/updateForm :: #update-container";
	}
	
	// 기존에 생성된 시험 정보 삭제
	@DeleteMapping("/examSubmit")
	@ResponseBody
	public String examInfoDelete(ExamVO vo, Model model) {
		examService.deleteExam(vo);
		return "success";
	}
	
	// 참고하기 위한 이전 강의들 시험 리스트 호출
	@PostMapping("/prevExamList")
	public String prevExamList(ExamInfoVO vo, Model model) {
		List<ExamInfoVO> examList = examService.getExamInfoList(vo);
		model.addAttribute("examList", examList);
		return "layout/fragments/professor-eclass/exam/prevExamList :: #prevExamList";
	}
	
	// 이전 시험 리스트의 시험지 호출 
	@PostMapping("/getCourseExam")
	public String getCourseExam(@RequestBody ExamFilterVO vo, Model model) {
		List<CourseExamVO> courseExamInfo = examService.getCourseExam(vo.getExam(), vo.getFilterQuestions());
		model.addAttribute("courseExam", courseExamInfo);
		return "layout/fragments/professor-eclass/exam/prevExamList :: #testQuestions";
	}
	
	// 새로운 시험 문제 생성
	@PostMapping("/createQuestion")
	public String createQuestion(@RequestBody QuestionVO vo, Model model) {
		examService.createQuestion(vo);
		model.addAttribute("question", vo);
		return "layout/fragments/professor-eclass/exam/newTestQuestion :: #newQuestion";
	}
	
	// 시험 문제 삭제
	@DeleteMapping("/deleteQuestion")
	@ResponseBody
	public String deleteQuestion(@RequestBody QuestionVO vo) {
		examService.deleteQuestion(vo);
		return "success";
	}
	
	// 시험 문제 변경
	@PostMapping("/updateQuestion")
	@ResponseBody
	public String updateQuestion(@RequestBody QuestionVO vo) {
		examService.updateQuestion(vo);
		return "success";
	}
	
	
	// 시험지 정보 저장 (임시저장, 시험지 생성하기)
	@PostMapping("/saveTest")
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
	
	// 시험지 생성하면 미리보기
	@RequestMapping({"/generateTestPaper/{examCode}", 
		"/examCheck/{examCode}"})
	public String generateTestPaper(@PathVariable String examCode, 
									ExamInfoVO vo, 
									ExamVO examVO, 
									Model model,
									HttpServletRequest request) {
		
		// 시험지 생성용인지 확인용인지 알아보기
		String path = request.getServletPath();
		if (path.contains("generateTestPaper")) {
			model.addAttribute("command", 0);
		} else if (path.contains("examCheck")) {
			model.addAttribute("command", 1);
		}
		
		// 시험 리스트 
		vo.setExamCode(examCode);
		model.addAttribute("examList", examService.getExamInfoList(vo));
		model.addAttribute("courseExamList", examService.getCourseExam(vo, null));
		return "professor/eclass/exam/examTestPaper";
	}
	
	// 시험지 최종 제출 (제출 여부 변경하기)
	@PostMapping("/finalSubmit")
	@ResponseBody
	public String finalSubmit(ExamInfoVO vo, Model model) {
		List<ExamScoreVO> studentList = mycourseService.getStudentList(vo);
		for (int i = 0; i < studentList.size(); i++) {
			studentList.get(i).setExamCode(vo.getExamCode());
		}
		// 제출 여부를 변경한 후 학생들의 시험 관련 데이터 생성 트랜잭션
		examService.finalExamSubmit(vo, studentList);
		return "completed";
	}

}
