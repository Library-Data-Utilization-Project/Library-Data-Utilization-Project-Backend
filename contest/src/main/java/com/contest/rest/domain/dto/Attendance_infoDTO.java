package com.contest.rest.domain.dto;

import lombok.Data;

@Data
public class Attendance_infoDTO {
	private int att_num;
	private String userId;
	private int LBRRY_SEQ_NO;
	private String att_date;
} 
