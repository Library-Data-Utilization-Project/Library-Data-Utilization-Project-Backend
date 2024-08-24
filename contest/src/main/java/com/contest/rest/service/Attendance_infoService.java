package com.contest.rest.service;

import java.util.List;

import com.contest.rest.domain.dto.Attendance_infoDTO;

public interface Attendance_infoService {
	// userId, thisMonth로 List<Ai> 가져오기
	List<Attendance_infoDTO> getAiList(String userId, String thisMonth);

}
