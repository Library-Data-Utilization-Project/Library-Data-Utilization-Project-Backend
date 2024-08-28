package com.contest.rest.service;

import com.contest.rest.domain.dto.BookmarkDTO;

public interface BookmarkService {

	// 특정 유저 북마크 추가하기.
	boolean addBm(BookmarkDTO bookmark);

	// 특정 유저의 특정 북마크 삭제하기.
	boolean deleteBm(String userId, String LBRRY_NAME);
	
}
