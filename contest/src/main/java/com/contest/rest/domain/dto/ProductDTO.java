package com.contest.rest.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductDTO {
	@JsonProperty("p_num")
	private int pNum;
	
	@JsonProperty("p_name")
	private String pName;
	
	@JsonProperty("p_info")
	private String pInfo;
	
	@JsonProperty("p_level")
	private int pLevel;
	
	@JsonProperty("p_cost")
	private int pCost;
	
	@JsonProperty("p_amount")
	private int pAmount;
}
