package com.project.portal.lecture.service;

import java.util.List;
import java.util.Map;

import com.project.portal.course.service.CourseVO;

public interface StudentLectureService {
	
	List<LectureVO> getLectureList(CourseVO course, LectureVO lecture);
	LectureVO getLecture(LectureVO vo);
	StudentLectureVO getLectureRecord(StudentLectureVO vo);
	void insertLectureRecord(StudentLectureVO vo);
	void updateLectureRecord(StudentLectureVO vo);
	StudentNoteVO insertStudentNote(StudentNoteVO vo);
	LectureQuestionVO insertLectureQuestion(LectureQuestionVO vo);
	Map<String, Object> getList(LectureVO vo, int studentId);
	List<StudentNoteVO> getNoteList(LectureVO lecture, int studentId);
	void deleteStudentNote(StudentNoteVO vo);
}
