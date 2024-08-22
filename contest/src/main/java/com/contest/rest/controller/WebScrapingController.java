package com.contest.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contest.rest.domain.dto.HotBorrow_infoDTO;
import com.contest.rest.service.WebScrapingService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/scrape")
public class WebScrapingController {
	/* 
	Create : 데이터 생성(POST)
	Read : 데이터 조회(GET)
	Update : 데이터 수정(PUT, PATCH)
	Delete : 데이터 삭제(DELETE)
	*/
	
	@Autowired
	private WebScrapingService wsservice;
	
	// 대출 급상승 조회
	@GetMapping("/hotBorrow")
	public  ResponseEntity<?> getHotBorrow() {
		List<HotBorrow_infoDTO> hb_list = wsservice.getHotBorrow();
		return ResponseEntity.status(200).body(hb_list);
	}
	
}
