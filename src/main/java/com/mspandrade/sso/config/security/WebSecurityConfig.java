package com.mspandrade.sso.config.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.mspandrade.sso.config.security.provider.UserAuthenticationProvider;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${app.signin-key}")
	private String signinKey;
	
	private UserAuthenticationProvider userAuthenticationProvider;
	
	@Autowired
	public WebSecurityConfig(UserAuthenticationProvider userAuthenticationProvider) {
		this.userAuthenticationProvider = userAuthenticationProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
			.jdbcAuthentication();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
	        .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	        .httpBasic()
	        .realmName("Simple realm")
	        .and()
	        .csrf()
	        .disable();
	        
	}
	
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
	    return new ProviderManager(Arrays.asList(userAuthenticationProvider));
	}
	
	@Bean 
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(signinKey);
		return converter;
	}

}
