package com.contest.rest.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Attendance_infoDTO {
	@JsonProperty("ai_num")
	private int aiNum;
	@JsonProperty("userId")
	private String userId;
	@JsonProperty("LBRRY_SEQ_NO")
	private int LBRRYSEQNO;
	@JsonProperty("ai_date")
	private String aiDate;
} 
