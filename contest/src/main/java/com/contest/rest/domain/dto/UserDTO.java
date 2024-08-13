package com.contest.rest.domain.dto;

import lombok.Data;

@Data
public class UserDTO {
	private String userId;
	private String userName;
	private String profile;
	private String level;
	private String point;
}
