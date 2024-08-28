package com.contest.rest.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.contest.rest.domain.dto.BookmarkDTO;

@Mapper
public interface BookmarkMapper {
	// Create
	int insertBmByUserid(BookmarkDTO bookmark);
	// Read
	// Update
	// Delete
	int deleteBmByUseriLBRRY_NAME(String userId, String LBRRY_NAME);


}
