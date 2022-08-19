package com.project.portal.lecture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.course.service.CourseVO;
import com.project.portal.lecture.service.LectureVO;
import com.project.portal.lecture.service.StudentLectureMapper;
import com.project.portal.lecture.service.StudentLectureService;

@Service
public class StudentLectureServiceImpl implements StudentLectureService {
	
	@Autowired StudentLectureMapper mapper;
	@Override
	public List<LectureVO> getLectureList(CourseVO vo) {
		return mapper.getLectureList(vo);
	}

}
