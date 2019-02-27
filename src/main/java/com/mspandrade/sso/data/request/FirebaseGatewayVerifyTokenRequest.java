package com.mspandrade.sso.data.request;

import lombok.Data;

@Data
public class FirebaseGatewayVerifyTokenRequest {

	private String clientToken;
	
	public FirebaseGatewayVerifyTokenRequest() {}
	
	public FirebaseGatewayVerifyTokenRequest(String clientToken) {
		this();
		this.clientToken = clientToken;
	}
	
}
