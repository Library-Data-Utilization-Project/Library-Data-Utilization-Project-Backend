package com.contest.rest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.contest.rest.domain.dto.Borrow_infoDTO;

@Mapper
public interface Borrow_infoMapper {
	// Create
	// Read
	List<Borrow_infoDTO> getBiListByUseridThisMonth(String userId, String thisMonth);
	// Update
	// Delete

}
