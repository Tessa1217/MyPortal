package com.project.portal.common.service;

import org.apache.ibatis.annotations.Mapper;

import com.project.portal.bachelor.service.BachelorScheduleVO;

@Mapper
public interface ScheduleMapper {
	void updateExamScore(BachelorScheduleVO vo);
	void updateLectureScore(BachelorScheduleVO vo);
	void updateReportScore(BachelorScheduleVO vo);
	void manageFile(BachelorScheduleVO vo);
}
