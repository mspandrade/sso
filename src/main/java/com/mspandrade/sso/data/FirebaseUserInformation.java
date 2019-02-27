package com.mspandrade.sso.data;

import java.io.Serializable;

import lombok.Data;

@Data
public class FirebaseUserInformation implements Serializable {

	private static final long serialVersionUID = 4518723346768806965L;
	
	private String uId;
	private String name;
	private String phoneNumber;
	private String email;
	private Boolean isEmailVerified;
	
}
