package com.contest.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contest.rest.domain.dto.BookmarkDTO;
import com.contest.rest.mapper.BookmarkMapper;

@Service
public class BookmarkServiceImpl implements BookmarkService{
	@Autowired
	private BookmarkMapper bmmapper;

	// 특정 유저 북마크 추가하기.
	@Override
	public boolean addBm(BookmarkDTO bookmark) {
		return bmmapper.insertBmByUserid(bookmark) == 1;
	}
	
}
