package com.contest.rest.service;

import java.util.List;

import com.contest.rest.domain.dto.Borrow_infoDTO;

public interface Borrow_infoService {
	// 특정 유저 대출 정보 List 뽑아오기.
	List<Borrow_infoDTO> getBiList(String userId, String thisMonth);

	// 대출 정보 추가하기.
	boolean addBi(String userId);

}
