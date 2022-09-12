package com.project.portal.lecture.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.portal.common.Criteria;
import com.project.portal.course.service.CourseVO;

@Mapper
public interface ProfessorLectureMapper {
	// 강의 리스트
	List<LectureVO> getLectureList(@Param("course") 
								CourseVO course, 
								@Param("lecture") 
								LectureVO lecture,
								@Param("cri")
								Criteria cri);
	// 강의 수
	int getLectureTotal(CourseVO course);
	// 강의 등록
	void insertLecture(LectureVO vo);
	// 비디오 업로드
	void uploadVideo(VideoVO vo);
	// 비디오 가져오기
	VideoVO getVideo(@Param("videoCode") String videoCode);
	// 예전 등록 비디오 파일 가져오기
	List<VideoVO> getVideoList(@Param("course") CourseVO course, 
							@Param("cri") Criteria cri);	
	// 비디오 수 
	int getVideoTotal(@Param("course") CourseVO course,
					@Param("cri") Criteria cri);
	// 강의 삭제 
	void deleteLecture(LectureVO vo);
	// 비디오 삭제
	void deleteVideo(VideoVO vo);
	// 강의 수정
	void updateLecture(LectureVO vo);

}
