package com.project.portal.mycourse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.common.Criteria;
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
	public List<MyCourseVO> getstuMyCourse(MyCourseVO vo) {
		// TODO Auto-generated method stub
		return mapper.getstuMyCourse(vo);
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

	public List<MyCourseVO> studentStudyList(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.studentStudyList(cri);
	}

	public MyCourseVO studentCreditSum(MyCourseVO vo) {
		// TODO Auto-generated method stub
		return mapper.studentCreditSum(vo);
	}

	@Override
	public List<myProfCourseVO> getProfMyCourse(myProfCourseVO vo) {
		// TODO Auto-generated method stub
		return mapper.getProfMyCourse(vo);
	}

	@Override
	public MyCourseMainVO getProfMyCoursePage(String courseCode) {
		// TODO Auto-generated method stub
		return mapper.getProfMyCoursePage(courseCode);
	}



}
