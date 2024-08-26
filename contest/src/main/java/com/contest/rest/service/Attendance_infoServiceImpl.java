package com.contest.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contest.rest.domain.dto.Attendance_infoDTO;
import com.contest.rest.mapper.Attendance_infoMapper;

@Service
public class Attendance_infoServiceImpl implements Attendance_infoService {
	@Autowired
	private Attendance_infoMapper atmapper;

	// userId, thisMonth로 List<Ai> 가져오기
	@Override
	public List<Attendance_infoDTO> getAiList(String userId, String thisMonth) {
		return atmapper.getAiListByUseridAndThisMonth(userId, thisMonth);
	}

	// userId, LBRRY_SEQ_NO로 특정 도서관 전체 방문 횟수 List<Ai>로 가져오기
	@Override
	public List<Attendance_infoDTO> getAiList(String userId, int LBRRY_SEQ_NO) {
		return atmapper.getAiListByUseridAndLBRRY_SEQ_NO(userId, LBRRY_SEQ_NO);
	}
	
	// 출석하기.
	@Override
	public boolean attendance(String userId, int LBRRY_SEQ_NO) {
		return atmapper.insertAiByUserIdAndLBRRY_SEQ_NO(userId, LBRRY_SEQ_NO) == 1;
	}
	
	// 오늘 출석 체크
	@Override
	public Attendance_infoDTO checkAttendance(String userId, String thisDay) {
		return atmapper.getAiByUserIdAndThisDay(userId, thisDay);
	}


}
