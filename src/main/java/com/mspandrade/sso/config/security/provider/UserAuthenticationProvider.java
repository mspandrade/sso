package com.mspandrade.sso.config.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.mspandrade.sso.authentication.AuthenticationFactory;
import com.mspandrade.sso.entity.UserEntity;
import com.mspandrade.sso.service.UserService;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider, AuthenticationManager {
	
	private AuthenticationFactory authenticationFactory;
	private UserService userService;
	
	@Autowired
	public UserAuthenticationProvider(
			UserService userService,
			AuthenticationFactory authenticationFactory
			) {
		this.userService = userService;
		this.authenticationFactory = authenticationFactory;
	}

	@Override
	public Authentication authenticate(Authentication authentication) {
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		UserEntity userEntity = userService.findByUsername(username);
		
		return authenticationFactory.instance(userEntity)
								    .authentication(
								    		username, 
								    		password
								    		);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
