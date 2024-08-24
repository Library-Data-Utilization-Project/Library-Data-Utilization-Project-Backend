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
	
	@Override
	public List<Borrow_infoDTO> getBiList(String userId, String thisMonth) {
		return bimapper.getBiListByUseridThisMonth(userId, thisMonth);
	}

}
