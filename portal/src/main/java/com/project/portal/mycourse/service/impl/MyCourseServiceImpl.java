package com.project.portal.mycourse.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.course.service.CourseVO;
import com.project.portal.exam.service.ExamInfoVO;
import com.project.portal.exam.service.ExamScoreVO;
import com.project.portal.mycourse.service.MyCourseMainVO;
import com.project.portal.mycourse.service.MyCourseMapper;
import com.project.portal.mycourse.service.MyCourseService;
import com.project.portal.mycourse.service.MyCourseVO;
import com.project.portal.mycourse.service.myCourseDetailVO;
import com.project.portal.mycourse.service.myProfCourseVO;
import com.project.portal.studynotice.service.StudyNoticeVO;


@Service
public class MyCourseServiceImpl implements MyCourseService {
	
	@Autowired
	MyCourseMapper mapper;

	@Override
	public List<MyCourseVO> getstuMyCourse(MyCourseVO mycourse, CourseVO course) {
		return mapper.getstuMyCourse(mycourse, course);
	}

	@Override
	public MyCourseMainVO getstuMyCoursePage(String courseCode) {
		return mapper.getstuMyCoursePage(courseCode);
	}

	@Override
	public myCourseDetailVO getstuMyCourseDetail(String courseCode) {
		return mapper.getstuMyCourseDetail(courseCode);
	}

	@Override
	public List<myCourseDetailVO> getstuMyCourseWeekDetail(String courseCode) {
		return mapper.getstuMyCourseWeekDetail(courseCode);
	}
	@Override
	public List<MyCourseVO> studentStudyList(MyCourseVO vo) {
		return mapper.studentStudyList(vo);
	}
	@Override
	public MyCourseVO studentCreditSum(MyCourseVO vo) {
		return mapper.studentCreditSum(vo);
	}
	@Override
	public List<MyCourseVO> studentSortationCredit(MyCourseVO vo) {
		return mapper.studentSortationCredit(vo);
	}
	@Override
	public List<MyCourseVO> semesterGradeSelect(MyCourseVO vo) {
		return mapper.semesterGradeSelect(vo);
	}

	@Override
	public List<myProfCourseVO> getProfMyCourse(CourseVO vo) {
		return mapper.getProfMyCourse(vo);
	}

	@Override
	public MyCourseMainVO getProfMyCoursePage(String courseCode) {
		return mapper.getProfMyCoursePage(courseCode);
	}

	@Override
	public List<ExamScoreVO> getStudentList(ExamInfoVO vo) {
		return mapper.getStudentList(vo);
	}

	@Override
	public Map<String, Object> getWeeklyList(CourseVO vo) {
		vo = mapper.getWeekCode(vo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lectureList", mapper.getLectureList(vo));
		map.put("reportList", mapper.getReportList(vo));
		map.put("examList", mapper.getExamList(vo));
		return map;
	}

	@Override
	public String getCourseName(String courseCode) {
		return mapper.getCourseName(courseCode);
	}

	@Override
	public List<StudyNoticeVO> getStudyNoticeList(String courseCode) {
		// TODO Auto-generated method stub
		return mapper.getStudyNoticeList(courseCode);
	}

	@Override
	public List<myProfCourseVO> getProfMyCourseYear(int professorId) {
		// TODO Auto-generated method stub
		return mapper.getProfMyCourseYear(professorId);
	}

	@Override
	public List<myProfCourseVO> getProfMyCourseSemester(int professorId) {
		// TODO Auto-generated method stub
		return mapper.getProfMyCourseSemester(professorId);
	}

	@Override
	public int getTotal(MyCourseVO vo) {
		// TODO Auto-generated method stub
		return mapper.getTotal(vo);
	}

	@Override
	public List<MyCourseVO> getStuCourseYear(int studentId) {
		// TODO Auto-generated method stub
		return mapper.getStuCourseYear(studentId);
	}

	@Override
	public List<MyCourseVO> getStuCourseSemester(int studentId) {
		// TODO Auto-generated method stub
		return mapper.getStuCourseSemester(studentId);
	}

}
