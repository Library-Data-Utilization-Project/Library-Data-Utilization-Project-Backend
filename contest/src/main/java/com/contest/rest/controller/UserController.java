package com.contest.rest.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contest.rest.domain.dto.Attendance_infoDTO;
import com.contest.rest.domain.dto.Average_monthly_borrowDTO;
import com.contest.rest.domain.dto.Borrow_infoDTO;
import com.contest.rest.domain.dto.UserDTO;
import com.contest.rest.service.Attendance_infoService;
import com.contest.rest.service.Average_monthly_borrowService;
import com.contest.rest.service.Borrow_infoService;
import com.contest.rest.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/user")
public class UserController {
	/* 
	Create : 데이터 생성(POST)
	Read : 데이터 조회(GET)
	Update : 데이터 수정(PUT, PATCH)
	Delete : 데이터 삭제(DELETE)
	*/
	
	@Autowired
	private UserService uservice;
	@Autowired
	private Attendance_infoService aiservice;
	@Autowired
	private Borrow_infoService biservice;
	@Autowired
	private Average_monthly_borrowService ambservice;
	
	// 로그인
	@PostMapping("/login")
	public ResponseEntity<?> join(HttpServletRequest request ,@RequestBody UserDTO user) throws Exception {
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", user.getUserId());
		
		UserDTO checkUser = uservice.getUser(user.getUserId());
		
		// 등록되지 않은 user이면
		if(checkUser == null) {
			uservice.join(user);
			UserDTO newUser = uservice.getUser(user.getUserId());
			
			System.out.println("회원가입 완료 : " + newUser);
			return ResponseEntity.status(200).body(newUser);
		}
		// 이미 등록되어있는 user이면
		else {
			System.out.println("이미 가입된 회원입니다 : " + checkUser);
			return ResponseEntity.status(200).body(checkUser);
		}
		
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		
		return new String();
	}
	
	// 유저 전체 조회
	@GetMapping()
	public ResponseEntity<?> getUsers() {
		List<UserDTO> userList = uservice.getUsers();
		return ResponseEntity.status(200).body(userList);
	}
	
	// 유저정보, 출석횟수, 대출횟수 조회
	@GetMapping("/count")
	public ResponseEntity<?> getMethodName(HttpServletRequest request) throws Exception {
		LocalDate now = LocalDate.now();
		String thisMonth = now.format(DateTimeFormatter.ofPattern("yyyy-MM")); // "2024-08" 형식
		
		// 1. session에서 loginUesr 가져오기.
		HttpSession session = request.getSession();
		String loginUser = "유저1";
//		String loginUser = (String)session.getAttribute("loginUser");
		
		// 2. UserDTO 가져오기.
		UserDTO luDTO = uservice.getUser(loginUser);
		
		// 3. 이번달 출석 퍼센트 구하기.
		List<Attendance_infoDTO> Ai_list = aiservice.getAiList(loginUser, thisMonth);
		int thisMounth_Ai = Ai_list.size();
		int thisMounth_Ai_p = (int)((thisMounth_Ai / 30.0) * 100); // 1달을 30일로 가정하여 퍼센트를 계산
		
		// 4. 이번달 대출 횟수 구하기.
		List<Borrow_infoDTO> Bi_list = biservice.getBiList(loginUser, thisMonth);
		int thisMounth_Bi = Bi_list.size();
		
		// 5. 청소년 평균 대출 횟수 - 이번달 대출 횟수
		Average_monthly_borrowDTO amb = ambservice.getAmb(thisMonth);
		int thisMounth_Bi_t = amb.getAverage() - thisMounth_Bi;
		
		
		// 5. 모든 정보 합치기
		List<Map<String, Object>> total = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("userId", luDTO.getUserId());
		map.put("userName", luDTO.getUserName());
		map.put("profile", luDTO.getProfile());
		map.put("level", luDTO.getLevel());
		map.put("exp", luDTO.getExp());
		map.put("point", luDTO.getPoint());
		map.put("thisMounth_Ai", thisMounth_Ai);
		map.put("thisMounth_Ai_p", thisMounth_Ai_p);
		map.put("thisMounth_Bi", thisMounth_Bi);
		map.put("thisMounth_Bi_t", thisMounth_Bi_t);
		
		total.add(map);
		
		return ResponseEntity.status(200).body(total);
	}
	
}
