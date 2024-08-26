package com.contest.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contest.rest.domain.dto.Average_monthly_borrowDTO;
import com.contest.rest.mapper.Average_monthly_borrowMapper;

@Service
public class Average_monthly_borrowServiceImpl implements Average_monthly_borrowService{
	@Autowired
	private Average_monthly_borrowMapper ambmapper; 
	
	// 특정 달 청소년 평균 대출수 가져오기.
	@Override
	public Average_monthly_borrowDTO getAmb(String thisMonth) {
		return ambmapper.getAmbByMonth(thisMonth);
	}

}
