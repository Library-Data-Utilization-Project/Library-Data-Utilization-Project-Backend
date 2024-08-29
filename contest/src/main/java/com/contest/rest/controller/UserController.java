package com.contest.rest.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contest.rest.domain.dto.Attendance_infoDTO;
import com.contest.rest.domain.dto.Average_monthly_borrowDTO;
import com.contest.rest.domain.dto.Borrow_infoDTO;
import com.contest.rest.domain.dto.MemberDataDTO;
import com.contest.rest.domain.dto.UserDTO;
import com.contest.rest.service.Attendance_infoService;
import com.contest.rest.service.Average_monthly_borrowService;
import com.contest.rest.service.Borrow_infoService;
import com.contest.rest.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
	public ResponseEntity<MemberDataDTO> join(HttpServletRequest request, HttpServletResponse response ,@RequestBody UserDTO user) throws Exception {
		String loginUser = user.getUserId();
		
		Cookie cookie = new Cookie("loginUser", loginUser);
		cookie.setPath("/");
		cookie.setMaxAge(1*60*60); // 1시간 저장
		response.addCookie(cookie);
		
		MemberDataDTO memberData = new MemberDataDTO();
		
		//DB에서 유저 정보가 있는지 확인.
		UserDTO checkUser = uservice.getUser(user.getUserId());
		
		// 등록되지 않은 user이면
		if(checkUser == null) {
			uservice.join(user);
			UserDTO newUser = uservice.getUser(user.getUserId());

			memberData.setSuccess(true);
			memberData.setMessage(loginUser+"님의 회원가입이 완료되었습니다. 환영합니다!");
			return new ResponseEntity<>(memberData, HttpStatus.OK);
		}
		// 이미 등록되어있는 user이면
		else {
			memberData.setSuccess(true);
			memberData.setMessage(loginUser+"님의 정보가 존재합니다. 또 오셨군요, 반갑습니다!");
			return new ResponseEntity<>(memberData, HttpStatus.OK);
		}
		
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public ResponseEntity<MemberDataDTO> logout(HttpServletRequest request, HttpServletResponse response) {
	    System.out.println("로그아웃됨");

	    // 기존 쿠키 찾기
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if ("loginUser".equals(cookie.getName())) {
	                // 쿠키 삭제 (유효기간을 0으로 설정)
	                cookie.setValue(null);
	                cookie.setMaxAge(0);
	                cookie.setPath("/"); // 쿠키의 경로를 설정합니다. 원래 쿠키가 설정된 경로와 일치해야 합니다.
	                response.addCookie(cookie);
	            }
	        }
	    }

	    // 응답 데이터 설정
	    MemberDataDTO memberData = new MemberDataDTO();
	    memberData.setSuccess(true);
	    memberData.setMessage("이용해주셔서 감사합니다!");
	    
	    return new ResponseEntity<>(memberData, HttpStatus.OK);
	}
	
	// 유저 전체 조회
	@GetMapping()
	public ResponseEntity<MemberDataDTO> getUsers() {
		MemberDataDTO memberData = new MemberDataDTO();
		
		List<UserDTO> userList = uservice.getUsers();
		memberData.setSuccess(true);
		memberData.setMessage("모든 정보의 유저를 불러왔습니다!");
		memberData.setResult(userList);
		return new ResponseEntity<>(memberData, HttpStatus.OK);
	}
	
	// 유저정보, 출석횟수, 대출횟수 조회
	@GetMapping("/count")
	public ResponseEntity<MemberDataDTO> getMethodName(HttpServletRequest request, @CookieValue("loginUser") String cookie ) throws Exception {
		LocalDate now = LocalDate.now();
		String thisMonth = now.format(DateTimeFormatter.ofPattern("yyyy-MM")); // "2024-08" 형식
		
		MemberDataDTO memberData = new MemberDataDTO();

		// 1. 쿠키에서 loginUesr 가져오기.
		String loginUser = cookie;
		
		// 2. UserDTO 가져오기.
		UserDTO luDTO = uservice.getUser(loginUser);
		
		if(luDTO != null) {
			// 3. 이번달 출석 퍼센트 구하기.
			List<Attendance_infoDTO> Ai_list = aiservice.getAiList(loginUser, thisMonth);
			int thisMounth_Ai = Ai_list.size();
			int thisMounth_Ai_p = (int)((thisMounth_Ai / 30.0) * 100); // 1달을 30일로 가정하여 퍼센트를 계산
			
			// 4. 이번달 대출 횟수 구하기.
			List<Borrow_infoDTO> Bi_list = biservice.getBiList(loginUser, thisMonth);
			int thisMounth_Bi = Bi_list.size();
			
			// 5. 청소년 평균 대출 횟수 - 이번달 대출 횟수
			Average_monthly_borrowDTO amb = ambservice.getAmb(thisMonth);
			int thisMounth_Bi_t = Math.abs(amb.getAverage() - thisMounth_Bi);
			
			// 6. 청소년 평균 대출횟수 구하기.
			int average_Bi = amb.getAverage();
			
			
			// 7. 모든 정보 합치기
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
			map.put("average_Bi", average_Bi);
			map.put("thisMounth_Bi", thisMounth_Bi);
			map.put("thisMounth_Bi_t", thisMounth_Bi_t);
			
			total.add(map);
			
			memberData.setSuccess(true);
			memberData.setMessage(loginUser+"님에 대한 모든 정보를 불러왔습니다.");
			memberData.setResult(total);
			return new ResponseEntity<>(memberData, HttpStatus.OK);
		}
		else {
			memberData.setSuccess(true);
			memberData.setMessage("존재하지 않는 유저입니다. 다시 로그인 하세요.");
			return new ResponseEntity<>(memberData, HttpStatus.OK);
		}
		
	}
	
}
