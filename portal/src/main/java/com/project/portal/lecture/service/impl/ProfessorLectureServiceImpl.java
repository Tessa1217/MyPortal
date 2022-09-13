package com.project.portal.lecture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.portal.common.Criteria;
import com.project.portal.course.service.CourseVO;
import com.project.portal.lecture.service.LectureVO;
import com.project.portal.lecture.service.ProfessorLectureMapper;
import com.project.portal.lecture.service.ProfessorLectureService;
import com.project.portal.lecture.service.VideoVO;

@Service
public class ProfessorLectureServiceImpl implements ProfessorLectureService {
	
	@Autowired ProfessorLectureMapper mapper;
	
	@Override
	public List<LectureVO> getLectureList(CourseVO course, LectureVO lecture, Criteria cri) {
		return mapper.getLectureList(course, lecture, cri);
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

	@Override
	@Transactional
	public void deleteLecture(LectureVO vo) {
		VideoVO video = new VideoVO();
		video.setVideoCode(vo.getVideoCode());
		mapper.deleteLecture(vo);
		mapper.deleteVideo(video);
	}

	@Override
	@Transactional
	public void updateLecture(LectureVO vo, VideoVO newFile) {
		mapper.deleteVideo(vo.getLectureFile());
		mapper.uploadVideo(newFile);
		vo.setVideoCode(newFile.getVideoCode());
		mapper.updateLecture(vo);
		
	}

	@Override
	public void updateLectureOnly(LectureVO vo) {
		VideoVO video = new VideoVO();
		if (vo.getVideoCode() != null && vo.getLectureYoutubePath() != null) {
			video = new VideoVO();
			video.setVideoCode(vo.getVideoCode());
			vo.setVideoCode(null);
		}
		mapper.updateLecture(vo);
		mapper.deleteVideo(video);
		
	}

	@Override
	public int getLectureTotal(CourseVO course) {
		return mapper.getLectureTotal(course);
	}

	@Override
	public List<VideoVO> getVideoList(CourseVO course, Criteria cri) {
		return mapper.getVideoList(course, cri);
	}

	@Override
	public int getVideoTotal(CourseVO course, Criteria cri) {
		return mapper.getVideoTotal(course, cri);
	}
	
	

}
