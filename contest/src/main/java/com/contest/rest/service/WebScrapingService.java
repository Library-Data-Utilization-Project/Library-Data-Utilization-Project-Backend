package com.contest.rest.service;

import java.util.List;

import com.contest.rest.domain.dto.HotBorrow_infoDTO;

public interface WebScrapingService {
	List<HotBorrow_infoDTO> getHotBorrow();
}
