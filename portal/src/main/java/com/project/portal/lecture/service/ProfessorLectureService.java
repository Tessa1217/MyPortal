package com.project.portal.lecture.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.portal.common.Criteria;
import com.project.portal.course.service.CourseVO;

public interface ProfessorLectureService {
	
	// 강의 리스트 가져오기
	List<LectureVO> getLectureList(CourseVO course, LectureVO lecture, Criteria cri);
	// 강의 수 
	int getLectureTotal(CourseVO course);
	// 강의 등록 
	void insertLecture(LectureVO vo);
	// 비디오 업로드 
	void uploadVideo(VideoVO vo);
	// 비디오 가져오기
	VideoVO getVideo(String videoCode);
	// 예전 비디오 리스트 가져오기
	List<VideoVO> getVideoList(CourseVO course, Criteria cri);	
	// 리스트 수
	int getVideoTotal(CourseVO course, Criteria cri);
	// 강의 삭제 
	void deleteLecture(LectureVO vo);
	// 강의 파일까지 변경
	void updateLecture(LectureVO vo, VideoVO newFile);
	// 강의 내용 관련만 변경 
	void updateLectureOnly(LectureVO vo);
}
