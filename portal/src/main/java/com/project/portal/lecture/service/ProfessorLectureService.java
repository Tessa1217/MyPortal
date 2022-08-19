package com.project.portal.lecture.service;

import java.util.List;

import com.project.portal.course.service.CourseVO;

public interface ProfessorLectureService {
	
	List<LectureVO> getLectureList(CourseVO vo);
	void insertLecture(LectureVO vo);
	void uploadVideo(VideoVO vo);

}
