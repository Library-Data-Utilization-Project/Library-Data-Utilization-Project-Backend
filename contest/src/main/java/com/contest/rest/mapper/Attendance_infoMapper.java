package com.contest.rest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.contest.rest.domain.dto.Attendance_infoDTO;

@Mapper
public interface Attendance_infoMapper {
	// Create
	
	// Read
	List<Attendance_infoDTO> getAiListByUseridAndThisMonth(String userId, String thisMonth);
	
	// Update
	
	// Delete

}
