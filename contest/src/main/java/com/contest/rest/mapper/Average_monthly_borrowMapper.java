package com.contest.rest.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.contest.rest.domain.dto.Average_monthly_borrowDTO;

@Mapper
public interface Average_monthly_borrowMapper {
	// Create
	// Read
	Average_monthly_borrowDTO getAmbByMonth(String thisMonth);
	// Update
	// Delete
}
