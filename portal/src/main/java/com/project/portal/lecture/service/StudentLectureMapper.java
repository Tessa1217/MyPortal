package com.project.portal.lecture.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.portal.course.service.CourseVO;

@Mapper
public interface StudentLectureMapper {
	
	List<LectureVO> getLectureList(@Param("course") CourseVO course,
									@Param("lecture") LectureVO lecture);
	LectureVO getLecture(LectureVO vo);
	StudentLectureVO getLectureRecord(StudentLectureVO vo);
	void insertLectureRecord(StudentLectureVO vo);
	void updateLectureRecord(StudentLectureVO vo);
	void insertStudentNote(StudentNoteVO vo);
	void insertLectureQuestion(LectureQuestionVO vo);
	StudentNoteVO getNote(StudentNoteVO vo);
	LectureQuestionVO getQuestion(LectureQuestionVO vo);
	List<StudentNoteVO> getNoteList(@Param("lecture") LectureVO vo, 
			@Param("studentId") int studentId);
	List<LectureQuestionVO> getQuestionList(@Param("lecture") LectureVO vo,
			@Param("studentId") int studentId);
	void deleteStudentNote(StudentNoteVO vo);
}

