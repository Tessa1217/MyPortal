package com.project.portal.lecture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.portal.course.service.CourseVO;
import com.project.portal.lecture.service.LectureVO;
import com.project.portal.lecture.service.StudentLectureMapper;
import com.project.portal.lecture.service.StudentLectureService;
import com.project.portal.lecture.service.StudentLectureVO;
import com.project.portal.lecture.service.StudentNoteVO;

@Service
public class StudentLectureServiceImpl implements StudentLectureService {
	
	@Autowired StudentLectureMapper mapper;
	
	@Override
	public List<LectureVO> getLectureList(CourseVO vo) {
		return mapper.getLectureList(vo);
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
	public List<StudentNoteVO> insertStudentNote(StudentNoteVO vo) {
		if (vo.getNoteContent() != null) {
			mapper.insertStudentNote(vo);
		}
		return mapper.getNoteList(vo);
	}

}
