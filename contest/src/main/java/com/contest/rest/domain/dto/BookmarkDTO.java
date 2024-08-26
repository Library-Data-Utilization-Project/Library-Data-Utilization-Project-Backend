package com.contest.rest.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BookmarkDTO {
	@JsonProperty("bm_num")
	private int bmNum;
	@JsonProperty("userId")
	private String userId;
	@JsonProperty("LBRRY_SEQ_NO")
	private int LBRRYSEQNO;
	@JsonProperty("LBRRY_NAME")
	private String LBRRYNAME;
	@JsonProperty("ADRES")
	private String ADRES;
	@JsonProperty("TEL_NO")
	private String TELNO;
}
