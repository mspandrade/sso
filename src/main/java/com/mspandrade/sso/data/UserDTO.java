package com.mspandrade.sso.data;

import lombok.Data;

@Data
public class UserDTO {

	private Long id;
	private String username;
	private String loginType;
	
	public UserDTO() {}
	
	public UserDTO(Long id, String username, String loginType) {
		this.id = id;
		this.username = username;
		this.loginType = loginType;
	}
	
}
