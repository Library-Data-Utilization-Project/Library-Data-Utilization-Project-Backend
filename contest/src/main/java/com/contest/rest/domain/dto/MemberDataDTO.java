package com.contest.rest.domain.dto;

import java.util.List;

import lombok.Data;

@Data
public class MemberDataDTO {
	private boolean success;
	private String message;
	private List<?> result;
}
