package com.contest.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contest.rest.domain.dto.Attendance_infoDTO;
import com.contest.rest.domain.dto.BookmarkDTO;
import com.contest.rest.domain.dto.MemberDataDTO;
import com.contest.rest.domain.dto.ProductDTO;
import com.contest.rest.domain.dto.UserDTO;
import com.contest.rest.service.Attendance_infoService;
import com.contest.rest.service.BookmarkService;
import com.contest.rest.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/bookmark")
public class BookmarkController {
	/* 
	Create : 데이터 생성(POST)
	Read : 데이터 조회(GET)
	Update : 데이터 수정(PUT, PATCH)
	Delete : 데이터 삭제(DELETE)
	*/
	
	@Autowired
	private BookmarkService bmservice;
	@Autowired
	private UserService uservice;
	@Autowired
	private Attendance_infoService aiservice;
	
	@PostMapping()
	public ResponseEntity<MemberDataDTO> addBookmark(@CookieValue("loginUser") String cookie, HttpServletRequest request ,@RequestBody BookmarkDTO bookmark) throws Exception {
		String loginUser = cookie;
		
		bookmark.setUserId(loginUser);
		
		MemberDataDTO memberData = new MemberDataDTO();

		// user 유무 체크
		if(uservice.getUser(loginUser) != null) {
			// 북마크 추가하기.
			bmservice.addBm(bookmark);
			
			// 이 도서관 방문 횟수를 "ai_count"로 구하기.
			List<Attendance_infoDTO> ai_list = aiservice.getAiList(loginUser, bookmark.getLBRRYSEQNO());
			int ai_count = ai_list.size();
			
			// 모든 정보 합치기
			List<Map<String, Object>> total = new ArrayList<>();
			Map<String, Object> map = new HashMap<>();
			map.put("userId", loginUser);
			map.put("LBRRY_SEQ_NO", bookmark.getLBRRYSEQNO());
			map.put("LBRRY_NAME", bookmark.getLBRRYNAME());
			map.put("ADRES", bookmark.getADRES());
			map.put("TEL_NO", bookmark.getTELNO());
			map.put("ai_count", ai_count);
		
			total.add(map);
						
			memberData.setSuccess(true);
			memberData.setMessage(loginUser+"님의 북마크에"+bookmark.getLBRRYNAME()+"이(가) 등록되었습니다.");
			return new ResponseEntity<>(memberData, HttpStatus.OK);
		}
		else {
			memberData.setSuccess(false);
			memberData.setMessage("존재하지 않는 유저입니다. 다시 로그인 하세요.");
			return new ResponseEntity<>(memberData, HttpStatus.OK);
		}
	}
	
	// 특정 유저의 특정 북마크 삭제
	@DeleteMapping("/{LBRRY_NAME}")
	public ResponseEntity<?> deleteBookmark(@CookieValue("loginUser") String cookie, HttpServletRequest request ,@PathVariable String LBRRY_NAME) throws Exception {
		// 쿠키값에서 loginUser가져오기.
		String loginUser = cookie;
		MemberDataDTO memberData = new MemberDataDTO();
		
		if(loginUser != null) {
			// loginUsr + LBRRY_NAME값으로 bookmarkDTO 데이터 검색
			bmservice.deleteBm(loginUser, LBRRY_NAME);
			memberData.setSuccess(true);
			memberData.setMessage(LBRRY_NAME+"번의 도서관에 대한 북마크가 정상적으로 지워졌습니다.");
			return new ResponseEntity<>(memberData, HttpStatus.OK);
			
		}
		else {
			memberData.setSuccess(false);
			memberData.setMessage("존재하지 않는 유저입니다. 다시 로그인 해주세요.");
			return new ResponseEntity<>(memberData, HttpStatus.OK);
		}
	
	}
	
}
