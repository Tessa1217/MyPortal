package com.project.portal.mycourse.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.portal.bachelor.service.BachelorScheduleVO;
import com.project.portal.course.service.CourseVO;
import com.project.portal.course.service.impl.CourseServiceImpl;
import com.project.portal.mycourse.service.MyCourseMainVO;
import com.project.portal.mycourse.service.MyCourseVO;
import com.project.portal.mycourse.service.myCourseDetailVO;
import com.project.portal.mycourse.service.myProfCourseVO;
import com.project.portal.mycourse.service.impl.MyCourseServiceImpl;
import com.project.portal.student.service.StudentVO;
import com.project.portal.student.service.impl.StudentServiceImpl;

// 작성자: 김진형, 박근형
@Controller
public class myCourseController {
	private static final Logger logger = LoggerFactory.getLogger(myCourseController.class);

	@Autowired MyCourseServiceImpl service;
	@Autowired CourseServiceImpl cservice;
	@Autowired StudentServiceImpl studService;	
	@Autowired CourseServiceImpl courseService;
	
	public CourseVO course(HttpSession session) {
		CourseVO course = new CourseVO();
		course.setCourseCode((String) session.getAttribute("courseCode"));
		course.setCourseName(null);
		return courseService.getWeeklyInfo(course);
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
	public String SemesterGradeSelect(MyCourseVO vo, Model model, HttpSession session) {
		vo.setStudentId((int) session.getAttribute("id"));
		vo.setCourseYear(2022);
		vo.setCourseSemester(2);
		model.addAttribute("semesterGradeSelect", service.semesterGradeSelect(vo));
		return "student/info/semesterGrade";
	}

	// 학생 수강 목록 조회

	@RequestMapping("student/courseList")
	public String getstuMyCourse(MyCourseVO vo, 
								BachelorScheduleVO schedule,
								Model model, 
								HttpSession session) {
		schedule.setDetailCode("BPLAN00");
		CourseVO course = cservice.getYearSemester(schedule);
		vo.setStudentId((int) session.getAttribute("id"));
		List<MyCourseVO> mycourseList = service.getstuMyCourse(vo, course);
		logger.info(mycourseList.toString());
		model.addAttribute("mycourseList", mycourseList);

		return "student/courseList";
	}

	// 교수 강의 목록 조회
	@RequestMapping("professor/courseList")
	public String getProfMyCourse(myProfCourseVO vo, 
									BachelorScheduleVO schedule,
									Model model,
									HttpSession session) {
		
		schedule.setDetailCode("BPLAN00");
		CourseVO course = cservice.getYearSemester(schedule);
		course.setProfessorId((int) session.getAttribute("id"));
		
		List<myProfCourseVO> mycourseList = service.getProfMyCourse(course);
		System.out.println(mycourseList);
		model.addAttribute("mycourseList", mycourseList);
		return "professor/courseList";
	}

	// 수강 강의 lms 메인 페이지 이동

	@RequestMapping("/student/eclass/{courseCode}")
	public String getstuMyCoursePage(@PathVariable String courseCode, 
									MyCourseMainVO vo, 
									Model model,
									HttpSession session) {
		String getCourseCode = service.getCourseName(courseCode);
		session.setAttribute("courseName", getCourseCode);
		session.setAttribute("courseCode", courseCode);
		
		CourseVO course = new CourseVO();
		course.setCourseCode(courseCode);
		Map<String, Object> map = service.getWeeklyList(course);
		model.addAttribute("map", map);
		System.out.println(map);
		StudentVO student = new StudentVO();
		student.setStudentId((int) session.getAttribute("id"));
		model.addAttribute("student", studService.studentInfoSelect(student));
		// model.addAttribute("myCourseMain", service.getstuMyCoursePage(courseCode));
		return "student/eclass/eclassmain";

	}

	// 교수 강좌 강의 lms 메인 페이지 이동
	@RequestMapping("/professor/eclass/{courseCode}")
	public String getProfMyCoursePage(@PathVariable String courseCode, MyCourseMainVO vo, Model model,
			HttpSession session) {
		String getCourseCode = service.getCourseName(courseCode);
		session.setAttribute("courseName", getCourseCode);
		session.setAttribute("courseCode", courseCode);

		// model.addAttribute("myCourseMain", service.getstuMyCoursePage(courseCode));
		return "professor/eclass/eclassmain";

	}

	// 강의 계획서 조회
	@RequestMapping("/student/eclass/courseDetail")
	public String selectCourseDetail(myCourseDetailVO vo, Model model, HttpSession session) {
		// 과목코드 세션설정
		String courseCode = (String)session.getAttribute("courseCode");
		vo.setCourseCode(courseCode);
		model.addAttribute("courseDetail", service.getstuMyCourseDetail(vo.getCourseCode()));
		model.addAttribute("courseWeekDetail", service.getstuMyCourseWeekDetail(vo.getCourseCode()));
		return "student/eclass/course/courseDetail";
	}

}
