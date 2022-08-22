package com.project.portal.lecture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.portal.course.service.CourseVO;
import com.project.portal.lecture.service.LectureVO;
import com.project.portal.lecture.service.ProfessorLectureMapper;
import com.project.portal.lecture.service.ProfessorLectureService;
import com.project.portal.lecture.service.VideoVO;

@Service
public class ProfessorLectureServiceImpl implements ProfessorLectureService {
	
	@Autowired ProfessorLectureMapper mapper;
	
	@Override
	public List<LectureVO> getLectureList(CourseVO vo) {
		return mapper.getLectureList(vo);
	}
	
	@Override
	public void insertLecture(LectureVO vo) {
		mapper.insertLecture(vo);
	}

	@Override
	public void uploadVideo(VideoVO vo) {
		mapper.uploadVideo(vo);
		
	}

	@Override
	public VideoVO getVideo(String videoCode) {
		return mapper.getVideo(videoCode);
	}

}
