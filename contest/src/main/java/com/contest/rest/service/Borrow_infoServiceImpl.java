package com.contest.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contest.rest.domain.dto.Borrow_infoDTO;
import com.contest.rest.mapper.Borrow_infoMapper;

@Service
public class Borrow_infoServiceImpl implements Borrow_infoService{
	@Autowired
	Borrow_infoMapper bimapper;
	
	// 특정 유저 대출 정보 List 뽑아오기.
	@Override
	public List<Borrow_infoDTO> getBiList(String userId, String thisMonth) {
		return bimapper.getBiListByUseridThisMonth(userId, thisMonth);
	}

	// 대출 정보 추가하기.
	@Override
	public boolean addBi(String userId) {
		return bimapper.insertBiByUserid(userId) == 1;
	}

}
