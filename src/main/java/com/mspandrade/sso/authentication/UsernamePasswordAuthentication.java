package com.mspandrade.sso.authentication;

import java.util.ArrayList;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mspandrade.sso.entity.UserEntity;

public class UsernamePasswordAuthentication implements AuthenticationStereotype {
	
	private PasswordEncoder passwordEncoder;
	private UserEntity userEntity;
	
	public UsernamePasswordAuthentication(PasswordEncoder passwordEncoder, UserEntity userEntity) {
		this.userEntity = userEntity;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication
	authentication(String username, String password) {
		
		if (!passwordEncoder.matches(password, userEntity.getPassword())) {
			throw new BadCredentialsException("1000");
		}
		
		return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
	}

}
