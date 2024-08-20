package com.contest.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contest.rest.domain.dto.UserDTO;
import com.contest.rest.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/user/*")
public class UserController {
	/* 
	Create : 데이터 생성(POST)
	Read : 데이터 조회(GET)
	Update : 데이터 수정(PUT, PATCH)
	Delete : 데이터 삭제(DELETE)
	*/
	
	@Autowired
	private UserService uservice;
	
	// 로그인
	@PostMapping("/login")
	public ResponseEntity<?> join(@RequestBody UserDTO user) {
		UserDTO checkUser = uservice.getUser(user.getUserId());
		
		// 등록되지 않은 user이면
		if(checkUser == null) {
			uservice.join(user);
			System.out.println("회원가입 완료 : " + user);
			return ResponseEntity.status(200).body(user);
		}
		// 이미 등록되어있는 user이면
		else {
			System.out.println("이미 가입된 회원입니다 : " + checkUser);
			return ResponseEntity.status(200).body(checkUser);
		}
	}
	
	// 유저 전체 조회
	@GetMapping()
	public ResponseEntity<?> getUsers() {
		List<UserDTO> userList = uservice.getUsers();
		return ResponseEntity.status(200).body(userList);
	}
	

}
