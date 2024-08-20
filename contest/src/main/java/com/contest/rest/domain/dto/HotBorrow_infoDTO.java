package com.contest.rest.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class HotBorrow_infoDTO {
	@JsonProperty("hb_IBSN")
	private String hb_IBSN;
	@JsonProperty("date")
	private String date;
	@JsonProperty("hb_num")
	private int hb_num;
	@JsonProperty("increase")
	private int increase;
	@JsonProperty("thisWeek_rank")
	private int thisWeek_rank;
	@JsonProperty("lestWeek_rank")
	private int lestWeek_rank;
	@JsonProperty("thumbnail_url")
	private String thumbnail_url;
	@JsonProperty("title_info")
	private String title_info;
}
