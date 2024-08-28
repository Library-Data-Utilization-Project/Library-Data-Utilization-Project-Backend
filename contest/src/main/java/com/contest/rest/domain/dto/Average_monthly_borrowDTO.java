package com.contest.rest.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Average_monthly_borrowDTO {
	@JsonProperty("month")
	private String month;
	@JsonProperty("average")
	private int average;
}
