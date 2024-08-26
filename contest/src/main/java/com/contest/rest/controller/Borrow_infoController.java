package com.contest.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contest.rest.domain.dto.Borrow_infoDTO;
import com.contest.rest.service.Borrow_infoService;
import com.contest.rest.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/borrow_info")
public class Borrow_infoController {
	/* 
	Create : 데이터 생성(POST)
	Read : 데이터 조회(GET)
	Update : 데이터 수정(PUT, PATCH)
	Delete : 데이터 삭제(DELETE)
	*/
	
	@Autowired
	private Borrow_infoService biservice;
	@Autowired
	private UserService uservice;
	
	// 특정 유저 대출 정보 등록.
	@PostMapping()
	public ResponseEntity<?> addBi(HttpServletRequest request, @RequestBody Borrow_infoDTO borrow_info) throws Exception {
		// 세션에서 loginUser 구하기.
		HttpSession session = request.getSession();
		String loginUser = (String)session.getAttribute("loginUser");
		
		// 로그인 유저 존재 여부 확인.
		if(uservice.getUser(loginUser) != null) {
			// 대출 정보 추가하기.
			biservice.addBi(loginUser);
			return ResponseEntity.ok(loginUser+"님의 대출 정보가 저장되었습니다.");
		}
		else {
			return ResponseEntity.badRequest().body("존재하지 않는 회원입니다. 다시 로그인 해주세요.");
			
		}
		
		
	}
	
}
