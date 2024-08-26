package com.contest.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contest.rest.domain.dto.UserDTO;
import com.contest.rest.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper umapper;
	
	// 회원가입
	@Override
	public boolean join(UserDTO user) {
		return umapper.insertUser(user) == 1;
	}
	
	// 특정 유저 조회
	@Override
	public UserDTO getUser(String userId) {
		return umapper.getUserById(userId);
	}

	// 유저 전체 조회
	@Override
	public List<UserDTO> getUsers() {
		return umapper.getUsers();
	}

	// 경험치 추가
	@Override
	public boolean addExp(String userId, int exp) {
		umapper.addExpById(userId, exp);
		
		UserDTO loginUserDTO = umapper.getUserById(userId);
		int logExp = Integer.parseInt(loginUserDTO.getExp());
		
		if(logExp >= 40) {
			umapper.resetExpById(userId);
			int level = 1;
			umapper.addLevelById(userId, level);
			return true;
		}
		else {
			return true;
		}
	}
}
