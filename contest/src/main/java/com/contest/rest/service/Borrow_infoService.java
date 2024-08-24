package com.contest.rest.service;

import java.util.List;

import com.contest.rest.domain.dto.Borrow_infoDTO;

public interface Borrow_infoService {
	// userId로 List<Borrow_infoDTO> 찾기.
	List<Borrow_infoDTO> getBiList(String userId, String thisMonth);

}
