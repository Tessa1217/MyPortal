package com.project.portal.lecture.service;

import java.util.List;

import com.project.portal.course.service.CourseVO;

public interface StudentLectureService {
	
	List<LectureVO> getLectureList(CourseVO vo);
	LectureVO getLecture(LectureVO vo);

}
