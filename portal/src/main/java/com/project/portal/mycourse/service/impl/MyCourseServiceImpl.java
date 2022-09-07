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


@Service
public class MyCourseServiceImpl implements MyCourseService {
	
	@Autowired
	MyCourseMapper mapper;

	@Override
	public List<MyCourseVO> getstuMyCourse(MyCourseVO mycourse, CourseVO course) {
		// TODO Auto-generated method stub
		return mapper.getstuMyCourse(mycourse, course);
	}

	@Override
	public MyCourseMainVO getstuMyCoursePage(String courseCode) {
		// TODO Auto-generated method stub
		return mapper.getstuMyCoursePage(courseCode);
	}

	@Override
	public myCourseDetailVO getstuMyCourseDetail(String courseCode) {
		// TODO Auto-generated method stub
		return mapper.getstuMyCourseDetail(courseCode);
	}

	@Override
	public List<myCourseDetailVO> getstuMyCourseWeekDetail(String courseCode) {
		// TODO Auto-generated method stub
		return mapper.getstuMyCourseWeekDetail(courseCode);
	}
	@Override
	public List<MyCourseVO> studentStudyList(MyCourseVO vo) {
		// TODO Auto-generated method stub
		return mapper.studentStudyList(vo);
	}
	@Override
	public MyCourseVO studentCreditSum(MyCourseVO vo) {
		// TODO Auto-generated method stub
		return mapper.studentCreditSum(vo);
	}
	@Override
	public List<MyCourseVO> studentSortationCredit(MyCourseVO vo) {
		// TODO Auto-generated method stub
		return mapper.studentSortationCredit(vo);
	}
	@Override
	public List<MyCourseVO> semesterGradeSelect(MyCourseVO vo) {
		// TODO Auto-generated method stub
		return mapper.semesterGradeSelect(vo);
	}

	@Override
	public List<myProfCourseVO> getProfMyCourse(CourseVO vo) {
		// TODO Auto-generated method stub
		return mapper.getProfMyCourse(vo);
	}

	@Override
	public MyCourseMainVO getProfMyCoursePage(String courseCode) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return mapper.getCourseName(courseCode);
	}

	




}
