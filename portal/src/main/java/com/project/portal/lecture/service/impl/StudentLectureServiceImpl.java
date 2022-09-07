package com.project.portal.lecture.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.project.portal.course.service.CourseVO;
import com.project.portal.lecture.service.LectureQuestionVO;
import com.project.portal.lecture.service.LectureVO;
import com.project.portal.lecture.service.StudentLectureMapper;
import com.project.portal.lecture.service.StudentLectureService;
import com.project.portal.lecture.service.StudentLectureVO;
import com.project.portal.lecture.service.StudentNoteVO;

@Service
public class StudentLectureServiceImpl implements StudentLectureService {
	
	@Autowired StudentLectureMapper mapper;

	@Override
	public List<LectureVO> getLectureList(CourseVO course, LectureVO lecture) {
		return mapper.getLectureList(course, lecture);
	}
	
	@Override
	public LectureVO getLecture(LectureVO vo) {
		return mapper.getLecture(vo);
	}

	@Transactional
	@Override
	public void insertLectureRecord(StudentLectureVO vo) {
		StudentLectureVO selectVO = mapper.getLectureRecord(vo);
		if (selectVO == null) {
			mapper.insertLectureRecord(vo);
		}
	}

	@Override
	public void updateLectureRecord(StudentLectureVO vo) {
		mapper.updateLectureRecord(vo);	
	}

	@Override
	public StudentLectureVO getLectureRecord(StudentLectureVO vo) {
		return mapper.getLectureRecord(vo);
	}
	
	@Transactional
	@Override
	public StudentNoteVO insertStudentNote(StudentNoteVO vo) {
		System.out.println(vo);
		mapper.insertStudentNote(vo);
		System.out.println(vo);
		return mapper.getNote(vo);
	}

	@Transactional
	@Override
	public Map<String, Object> getList(LectureVO vo, int studentId) {
		List<StudentNoteVO> noteList = mapper.getNoteList(vo, studentId);
		List<LectureQuestionVO> questionList = mapper.getQuestionList(vo, studentId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("noteList", noteList);
		map.put("questionList", questionList);
		return map;
	}

	@Override
	public LectureQuestionVO insertLectureQuestion(LectureQuestionVO vo) {
		mapper.insertLectureQuestion(vo);
		return mapper.getQuestion(vo);
	}

	@Override
	public List<StudentNoteVO> getNoteList(LectureVO lecture, int studentId) {
		return mapper.getNoteList(lecture, studentId);
	}

	@Override
	public void deleteStudentNote(StudentNoteVO vo) {
		mapper.deleteStudentNote(vo);
	}
	
	

}
