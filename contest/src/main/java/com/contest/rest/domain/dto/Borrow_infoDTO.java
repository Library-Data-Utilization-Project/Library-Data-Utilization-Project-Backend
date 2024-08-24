package com.contest.rest.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Borrow_infoDTO {
	@JsonProperty("bi_num")
	private int biNum;
	@JsonProperty("userId")
	private String userId;
	@JsonProperty("borrow_day")
	private String borrowDay;
}
