package com.contest.rest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.contest.rest.domain.dto.Attendance_infoDTO;

@Mapper
public interface Attendance_infoMapper {
	// Create
	int insertAiByUserIdAndLBRRY_SEQ_NO(String userId, int LBRRY_SEQ_NO);
	
	// Read
	List<Attendance_infoDTO> getAiListByUseridAndThisMonth(String userId, String thisMonth);
	Attendance_infoDTO getAiByUserIdAndThisDayAndlBRRY_SEQ_NO(String userId, String thisDay, int lBRRY_SEQ_NO);
	List<Attendance_infoDTO> getAiListByUseridAndLBRRY_SEQ_NO(String userId, int LBRRY_SEQ_NO);
	
	// Update
	
	// Delete

}
