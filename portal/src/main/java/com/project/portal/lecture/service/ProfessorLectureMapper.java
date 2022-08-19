package com.project.portal.lecture.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.portal.course.service.CourseVO;

@Mapper
public interface ProfessorLectureMapper {
	
	List<LectureVO> getLectureList(CourseVO vo);
	void insertLecture(LectureVO vo);
	void uploadVideo(VideoVO vo);

}
