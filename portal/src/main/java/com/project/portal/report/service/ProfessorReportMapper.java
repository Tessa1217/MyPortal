package com.project.portal.report.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.portal.common.Criteria;
import com.project.portal.course.service.CourseVO;
import com.project.portal.student.service.StudentVO;

@Mapper
public interface ProfessorReportMapper {
	
	// 과제 관련 
	
	// 과제 리스트 가져오기
	List<ReportVO> getReportList(@Param("course") CourseVO vo,
								@Param("report") ReportVO report,
								@Param("cri") Criteria cri);
	
	// 과제 수 가져오기 
	int getTotal(@Param("course") CourseVO course, 
				@Param("cri") Criteria cri);

	// 과제 리스트 등록
	void insertReport(ReportVO vo);

	// 과제 수정하기
	void updateReport(ReportVO vo);

	// 과제 삭제하기
	void deleteReport(ReportVO vo);
	
	// 파일 관련 
	
	// 전체 파일 가져오기 
	List<ReportFileVO> getProfWholeFile(@Param("file") ReportFileVO vo, 
										@Param("cri") Criteria cri);
	
	// 파일 수 
	int getFileTotal(@Param("file") ReportFileVO vo, 
					@Param("cri") Criteria cri);	
	
	// 파일 업로드
	void uploadFile(ReportFileVO vo);

	// 파일 가져오기
	ReportFileVO getFile(@Param("reportFileCode") String reportFileCode, @Param("userCode") String userCode);

	// 파일 삭제
	void deleteFile(ReportFileVO vo);

	// 수강생 과제 제출 관련 
	
	// 수강생 목록
	List<StudentVO> getStudentList(@Param("report") ReportVO vo, 
								@Param("submission") ReportSubmissionVO submission);

	// 수강생의 과제 제출 등록하기
	void insertStudentSubmission(@Param("report") ReportVO vo, @Param("student") StudentVO student);

	// 수강생의 과제 제출 목록
	List<ReportSubmissionVO> getStudentReportList(@Param("list") List<ReportVO> list, 
									@Param("cri") Criteria cri);
	
	// 과제 제출 수
	int getReportTotal(@Param("list") List<ReportVO> list, @Param("cri") Criteria cri);
	
	// 과제 성적 수정
	void updateScore(ReportSubmissionVO sub);
	
	// 과제 이의신청 목록 조회
	public List<ReportObjectionVO> getStudentObjectionList(@Param("reportObjection") ReportObjectionVO vo, @Param("cri") Criteria cri);
	// 과제 이의신청 상세 조회
	public ReportObjectionVO getStudentObjectionDetail(ReportObjectionVO vo);
	// 과제 이의신청 처리
	public void updateObjection(ReportObjectionVO vo);
	// 과제 점수 update
	public void updateObjectionScore(ReportObjectionVO vo);
	// 과제 이의신청 총 개수 조회(페이징 처리)
	public int getReportObjectionTotal(@Param("ReportObject") ReportObjectionVO vo ,@Param("cri") Criteria cri );

}
