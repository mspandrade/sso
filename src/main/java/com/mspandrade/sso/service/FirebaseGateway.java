package com.mspandrade.sso.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mspandrade.sso.data.FirebaseUserInformation;
import com.mspandrade.sso.data.request.FirebaseGatewayVerifyTokenRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FirebaseGateway {
	
	@Value("${firebase-gateway.url}")
	private String url;
	
	@Value("${firebase-gateway.verify-token-path}")
	private String verifyTokenPath;
	
	private Gson gson;
	
	public FirebaseGateway() {
		gson = new Gson();
	}
	
	public FirebaseUserInformation validateToken(String firebaseToken) {
		
		FirebaseUserInformation userInfo = null;
		
		try {
			
			String body = gson.toJson(new FirebaseGatewayVerifyTokenRequest(firebaseToken));
			
			HttpResponse<String> response = Unirest.post(url + verifyTokenPath)
													.header("Content-Type", "application/json")
													.body(body)
							  						.asString();
			
			if (HttpStatus.OK.value() == response.getStatus()) {
			
				userInfo = gson.fromJson(response.getBody(), FirebaseUserInformation.class);
				
			} else {
				
				log.info("RESPOSTA COM ERRO: " + response.getBody());
				log.info("TOKEN INVALIDO " + firebaseToken);
			}
			
		} catch (UnirestException e) {
			log.error("ERRO AO VERIFICAR TOKEN EM FIREBASE GATEWAY", e);
		}
		
		return userInfo;
	}

}
