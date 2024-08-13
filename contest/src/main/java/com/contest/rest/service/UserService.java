package com.contest.rest.service;

import com.contest.rest.domain.dto.UserDTO;

public interface UserService {
	// 회원가입
	boolean join(UserDTO user);
	// 특정 유저 조회
	UserDTO getUser(String userId);
}
