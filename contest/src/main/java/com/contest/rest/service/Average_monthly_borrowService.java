package com.contest.rest.service;

import com.contest.rest.domain.dto.Average_monthly_borrowDTO;

public interface Average_monthly_borrowService {
	// 특정 달 청소년 평균 대출수 가져오기.
	Average_monthly_borrowDTO getAmb(String thisMonth);

}
