package com.mspandrade.sso.authentication;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mspandrade.sso.entity.UserEntity;
import com.mspandrade.sso.enums.LoginType;
import com.mspandrade.sso.service.FirebaseGateway;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthenticationFactory {
	
	private FirebaseGateway firebaseGateway;
	private PasswordEncoder passwordEncoder;
	
	public AuthenticationFactory(
			FirebaseGateway firebaseGateway,
			PasswordEncoder passwordEncoder
			) {
		this.firebaseGateway = firebaseGateway;
		this.passwordEncoder = passwordEncoder;
	}

	public AuthenticationStereotype instance(UserEntity userEntity) {
		
		if (userEntity == null) {
			throw new BadCredentialsException("Wrong access");
		}
		
		LoginType loginType = userEntity.getLoginType();
		
		AuthenticationStereotype auth;
		
		switch (loginType) {

			case PHONE_NUMBER:
				
				auth = new FirebasePhoneNumberAuthentication(firebaseGateway);
				
				break;
			case USERNAME_PASSWORD:	
				
				auth = new UsernamePasswordAuthentication(
						passwordEncoder, 
						userEntity
						);
				
				break;
			default:
				log.error("TIPO DE LOGIN NAO RECONHECIDO " + loginType.name());
				throw new BadCredentialsException("1001");
		}
		
		return auth;
	}
	
}
