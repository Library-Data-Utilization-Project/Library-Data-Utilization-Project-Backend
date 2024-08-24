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

	@Override
	// userId, thisMonth로 List<Ai> 가져오기
	public List<Attendance_infoDTO> getAiList(String userId, String thisMonth) {
		return atmapper.getAiListByUseridAndThisMonth(userId, thisMonth);
	}

}
