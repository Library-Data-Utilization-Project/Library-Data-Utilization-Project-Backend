package com.contest.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contest.rest.domain.dto.Borrow_infoDTO;
import com.contest.rest.domain.dto.MemberDataDTO;
import com.contest.rest.service.Borrow_infoService;
import com.contest.rest.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CookieValue;
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
	public ResponseEntity<MemberDataDTO> addBi(@CookieValue("loginUser") String cookie, HttpServletRequest request, @RequestBody Borrow_infoDTO borrow_info) throws Exception {
		// 세션에서 loginUser 구하기.
		String loginUser = cookie;
		
		MemberDataDTO memberData = new MemberDataDTO();
		
		// 로그인 유저 존재 여부 확인.
		if(uservice.getUser(loginUser) != null) {
			// 대출 정보 추가하기.
			biservice.addBi(loginUser);
			
			memberData.setSuccess(true);
			memberData.setMessage(loginUser+"님의 대출 정보가 등록되었습니다.");
			return new ResponseEntity<>(memberData, HttpStatus.OK);
		}
		else {
			memberData.setSuccess(false);
			memberData.setMessage("존재하지 않는 회원입니다. 다시 로그인 하세요.");
			return new ResponseEntity<>(memberData, HttpStatus.OK);
			
		}
		
		
	}
	
}
