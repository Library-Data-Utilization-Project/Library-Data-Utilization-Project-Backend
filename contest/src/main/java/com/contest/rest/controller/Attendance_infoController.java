package com.contest.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contest.rest.domain.dto.Attendance_infoDTO;
import com.contest.rest.domain.dto.UserDTO;
import com.contest.rest.service.Attendance_infoService;
import com.contest.rest.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/attendance_info")
public class Attendance_infoController {
	/* 
	Create : 데이터 생성(POST)
	Read : 데이터 조회(GET)
	Update : 데이터 수정(PUT, PATCH)
	Delete : 데이터 삭제(DELETE)
	*/
	@Autowired
	private Attendance_infoService aiservice;
	@Autowired
	private UserService uservice;
	
	// 출석하기.
	@PostMapping()
	public ResponseEntity<?> attendance(@CookieValue("loginUser") String cookie ,HttpServletRequest request, @RequestBody Attendance_infoDTO Ai) throws Exception {
		String loginUser = cookie;
		
		int LBRRY_SEQ_NO = Ai.getLBRRYSEQNO();
		
		LocalDate now = LocalDate.now();
		String thisDay = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // "2024-08-25" 형식

		// 1. loginUser가 오늘 출석 했는지 확인.
		if(aiservice.checkAttendance(loginUser, thisDay) == null) {
			// 2. 오늘 출석 안했다면 출석 정보 등록.
			aiservice.attendance(loginUser, LBRRY_SEQ_NO);
			// 3. 경험치 +10 추가
			uservice.addExp(loginUser, 10);
			return ResponseEntity.ok("출석체크 및  완료.");
		}
		else {
			return ResponseEntity.badRequest().body("오늘은 이미 출석을 했습니다.");
		}
	}
	
}
