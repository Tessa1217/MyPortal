package com.project.portal.lecture.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.portal.course.service.CourseVO;

@Mapper
public interface StudentLectureMapper {
	
	List<LectureVO> getLectureList(CourseVO vo);
	LectureVO getLecture(LectureVO vo);

}
