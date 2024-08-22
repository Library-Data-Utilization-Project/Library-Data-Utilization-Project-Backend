package com.contest.rest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.contest.rest.domain.dto.UserDTO;

@Mapper
public interface UserMapper {
	// Create
	int insertUser(UserDTO user);
	// Read
	UserDTO getUserById(String userId);
	List<UserDTO> getUsers();
	// Update
	// Delete
}
