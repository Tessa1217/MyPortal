package com.project.portal.mycourse.web;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.bachelor.service.BachelorScheduleService;
import com.project.portal.bachelor.service.BachelorScheduleVO;
import com.project.portal.common.PageDTO;
import com.project.portal.common.service.CodeService;
import com.project.portal.common.service.CodeVO;
import com.project.portal.course.service.CourseService;
import com.project.portal.course.service.CourseVO;
import com.project.portal.mycourse.service.MyCourseMainVO;
import com.project.portal.mycourse.service.MyCourseService;
import com.project.portal.mycourse.service.MyCourseVO;
import com.project.portal.mycourse.service.myCourseDetailVO;
import com.project.portal.mycourse.service.myProfCourseVO;
import com.project.portal.professor.service.ProfessorService;
import com.project.portal.professor.service.ProfessorVO;
import com.project.portal.student.service.StudentService;
import com.project.portal.student.service.StudentVO;
import com.project.portal.studynotice.service.StudyNoticeVO;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

// 작성자: 김진형, 박근형, 권유진 
@Controller
public class myCourseController {
	private static final Logger logger = LoggerFactory.getLogger(myCourseController.class);

	@Autowired
	MyCourseService service;
	@Autowired
	CourseService cservice;
	@Autowired
	StudentService studService;
	@Autowired
	ProfessorService profService;
	@Autowired
	CourseService courseService;
	@Autowired
	BachelorScheduleService scheduleService;
	@Autowired
	CodeService codeService;

	@ModelAttribute("schedule")
	public BachelorScheduleVO getSchedule() {
		BachelorScheduleVO schedule = new BachelorScheduleVO();
		schedule.setDetailCode("BPLAN00");
		return scheduleService.getScheduleInfo(schedule);
	}

	// 학생 학업 정보 조회

	@RequestMapping("/student/studentStudyList")
	public String StudentStudyInfoList(MyCourseVO vo, Model model, HttpSession session) {
		vo.setStudentId((int) session.getAttribute("id"));
		model.addAttribute("studentStudyList", service.studentStudyList(vo)); // 학생 학기별 성적 리스트 조회
		model.addAttribute("studentCreditSum", service.studentCreditSum(vo)); // 학생 전체 취득 학점 조회
		model.addAttribute("studentSortationCredit", service.studentSortationCredit(vo)); // 이수구분 별 총 취득학점
		return "student/info/grade";
	}

	// 학기별 성적 조회

	@RequestMapping("/student/semesterGradeSelect")
	public String SemesterGradeSelect(MyCourseVO vo, Model model, BachelorScheduleVO voo, HttpSession session) {

		List<CodeVO> codeList = new ArrayList<CodeVO>();
		codeList = codeService.getDetailList("C01");
		vo.setStudentId((int) session.getAttribute("id"));
		model.addAttribute("semesterGradeSelect", service.semesterGradeSelect(vo));

		// 성적 이의 신청 기간
		voo.setDetailCode("REG02");
		model.addAttribute("gradeYearSemester", scheduleService.getScheduleInfo(voo));

		model.addAttribute("pageMaker", new PageDTO(service.getTotal(vo), vo.getAmount(), vo));
		return "student/info/semesterGrade";
	}

	// 학생 수강 목록 조회

	@RequestMapping("student/courseList")
	public String getstuMyCourse(MyCourseVO vo, CourseVO course, BachelorScheduleVO schedule, Model model,
			HttpSession session) {
		vo.setStudentId((int) session.getAttribute("id"));
		schedule = (BachelorScheduleVO) model.getAttribute("schedule");
		course.setCourseYear((int) session.getAttribute("year"));
		course.setCourseSemester((int) session.getAttribute("semester"));

		int studId = (int) session.getAttribute("id");

		
		model.addAttribute("searchYearVal", course.getSearchYear());
		model.addAttribute("searchSemesterVal", course.getSearchSemester());
		
		
		model.addAttribute("year", service.getStuCourseYear(studId));
		model.addAttribute("semester", service.getStuCourseSemester(studId));
		model.addAttribute("mycourseList", service.getstuMyCourse(vo, course));
		return "student/courseList";
	}

	// 교수 강의 목록 조회
	@RequestMapping("professor/courseList")
	public String getProfMyCourse(myProfCourseVO vo, CourseVO course, BachelorScheduleVO schedule, Model model,
			HttpSession session) {
		course.setProfessorId((int) session.getAttribute("id"));
		schedule = (BachelorScheduleVO) model.getAttribute("schedule");
		course.setCourseYear((int) session.getAttribute("year"));
		course.setCourseSemester((int) session.getAttribute("semester"));
		int profId = (int) session.getAttribute("id");
		
		model.addAttribute("searchYearVal", course.getSearchYear());
		model.addAttribute("searchSemesterVal", course.getSearchSemester());
		
		model.addAttribute("year", service.getProfMyCourseYear(profId));
		model.addAttribute("semester", service.getProfMyCourseSemester(profId));
		
		
		
		
		model.addAttribute("mycourseList", service.getProfMyCourse(course));
		return "professor/courseList";
	}

	// 수강 강의 lms 메인 페이지 이동

	@RequestMapping("/student/eclass/{courseCode}")
	public String getstuMyCoursePage(@PathVariable String courseCode, CourseVO course, MyCourseMainVO vo,
			MyCourseVO mycourse, StudyNoticeVO svo, StudentVO student, Model model, HttpSession session,
			BachelorScheduleVO schedule) {

		// 강의 정보
		course.setCourseCode(courseCode);
		session.setAttribute("courseCode", courseCode);
		session.setAttribute("courseInfo", cservice.getWeeklyInfo(course));

		// 주차별 과제, 강의, 시험 정보
		Map<String, Object> map = service.getWeeklyList(course);
		model.addAttribute("map", map);

		// 최근 강의 공지사항 정보 조회
		model.addAttribute("courseNotice", service.getStudyNoticeList(courseCode));

		// 학생이 수강중인 전체 강의 목록
		/*
		 * schedule.setDetailCode("BPLAN00"); course =
		 * cservice.getYearSemester(schedule); mycourse.setStudentId((int)
		 * session.getAttribute("id"));
		 */
		
		mycourse.setStudentId((int) session.getAttribute("id"));
		schedule = (BachelorScheduleVO) model.getAttribute("schedule");
		course.setCourseYear((int) session.getAttribute("year"));
		course.setCourseSemester((int) session.getAttribute("semester"));
		session.setAttribute("courseList", service.getstuMyCourse(mycourse, course));
		
		

		// 학생 정보
		student.setStudentId((int) session.getAttribute("id"));
		model.addAttribute("student", studService.studentInfoSelect(student));

		return "student/eclass/eclassmain";

	}

	// 교수 강좌 강의 lms 메인 페이지 이동
	@RequestMapping("/professor/eclass/{courseCode}")
	public String getProfMyCoursePage(@PathVariable String courseCode, CourseVO course, MyCourseMainVO vo,
			ProfessorVO professor, StudyNoticeVO svo, Model model, HttpSession session, BachelorScheduleVO schedule) {

		// 강의 정보
		course.setCourseCode(courseCode);
		session.setAttribute("courseCode", courseCode);
		session.setAttribute("courseInfo", cservice.getWeeklyInfo(course));

		// 주차별 과제, 강의, 시험 정보
		Map<String, Object> map = service.getWeeklyList(course);
		model.addAttribute("map", map);

		// 최근 강의 공지사항 정보 조회
		model.addAttribute("courseNotice", service.getStudyNoticeList(courseCode));

		// 교수 정보
		professor.setProfessorId((int) session.getAttribute("id"));
		model.addAttribute("professor", profService.adminProfessorInfoSelect(professor));

		// 전체 강의 목록
		
		course.setProfessorId((int) session.getAttribute("id"));
		schedule = (BachelorScheduleVO) model.getAttribute("schedule");
		course.setCourseYear((int) session.getAttribute("year"));
		course.setCourseSemester((int) session.getAttribute("semester"));
		session.setAttribute("courseList", service.getProfMyCourse(course));
		return "professor/eclass/eclassmain";

	}

	// 강의 계획서 조회
	@RequestMapping("/student/eclass/courseDetail")
	public String selectCourseDetail(myCourseDetailVO vo, Model model, HttpSession session) {
		// 과목코드 세션설정
		String courseCode = (String) session.getAttribute("courseCode");
		vo.setCourseCode(courseCode);
		model.addAttribute("courseDetail", service.getstuMyCourseDetail(vo.getCourseCode()));
		model.addAttribute("courseWeekDetail", service.getstuMyCourseWeekDetail(vo.getCourseCode()));
		return "student/eclass/course/courseDetail";
	}

	@Autowired
	DataSource datasource;

	// 강의 계획서 pdf
	@RequestMapping("/student/eclass/pdf/{courseCode}")
	public void pdfReport(HttpServletRequest request, HttpServletResponse response, @PathVariable String courseCode,
			Model model) throws Exception {
		Connection conn = datasource.getConnection();
		InputStream stream1 = getClass().getResourceAsStream("/reports/course.jrxml");
		InputStream stream2 = getClass().getResourceAsStream("/reports/course_weekjrxml.jrxml");
		response.setContentType("application/pdf");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("courseCode", courseCode);
		JasperReport jasperReport = JasperCompileManager.compileReport(stream1);
		JasperReport subReport = JasperCompileManager.compileReport(stream2);
		map.put("subReport", subReport);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn);
		JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
	}

}
