package com.contest.rest.service;

import java.util.List;

import com.contest.rest.domain.dto.UserDTO;

public interface UserService {
	// 회원가입
	boolean join(UserDTO user);
	// 특정 유저 조회
	UserDTO getUser(String userId);
	// 유저 전체 조회
	List<UserDTO> getUsers();
}
