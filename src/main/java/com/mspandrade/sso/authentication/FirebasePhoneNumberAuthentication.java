package com.mspandrade.sso.authentication;

import java.util.Arrays;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.mspandrade.sso.data.FirebaseUserInformation;
import com.mspandrade.sso.service.FirebaseGateway;

public class FirebasePhoneNumberAuthentication implements AuthenticationStereotype {
	
	private FirebaseGateway firebaseGateway;

	public FirebasePhoneNumberAuthentication(FirebaseGateway firebaseGateway) {
		this.firebaseGateway = firebaseGateway;
	}
	
	@Override
	public Authentication authentication(String username, String password) {
		
		FirebaseUserInformation userInfo = firebaseGateway.validateToken(password);
		
		if (userInfo == null || !userInfo.getPhoneNumber().equals(username)) {
			throw new BadCredentialsException("Invalid firebase token");
		}
		
		return new UsernamePasswordAuthenticationToken(username, "", Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
	}
	
}
