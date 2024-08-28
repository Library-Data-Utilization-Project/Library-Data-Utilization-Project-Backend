package com.contest.rest.service;

import java.util.List;

import com.contest.rest.domain.dto.Attendance_infoDTO;

public interface Attendance_infoService {
	// userId, thisMonth로 List<Ai> 가져오기
	List<Attendance_infoDTO> getAiList(String userId, String thisMonth);
	// userId로 List<Ai> 가져오기
	List<Attendance_infoDTO> getAiList(String userId, int LBRRY_SEQ_NO);
	
	// 출석하기.
	boolean attendance(String userId, int LBRRY_SEQ_NO);

	// 오늘 특정 도서관 출석 확인.
	Attendance_infoDTO checkAttendance(String userId, String thisDay, int LBRRY_SEQ_NO);


}
