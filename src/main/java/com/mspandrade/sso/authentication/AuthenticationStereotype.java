package com.mspandrade.sso.authentication;

import org.springframework.security.core.Authentication;

public interface AuthenticationStereotype {

	Authentication authentication(String username, String password);
	
}
