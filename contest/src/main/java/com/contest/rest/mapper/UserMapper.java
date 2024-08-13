package com.contest.rest.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.contest.rest.domain.dto.UserDTO;

@Mapper
public interface UserMapper {
	// Create
	int insertUser(UserDTO user);
	// Read
	UserDTO getUserById(String userId);
	// Update
	// Delete
}
