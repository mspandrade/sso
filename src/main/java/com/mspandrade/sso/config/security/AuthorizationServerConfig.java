package com.mspandrade.sso.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Value("${app.signin-key}")
	private String signinKey;
	
	private AuthenticationManager authenticationManager;
	private PasswordEncoder passwordEncoder;
	private DataSource dataSource;
	private UserDetailsService userDetailsService;
	private JwtAccessTokenConverter jwtAccessTokenConverter;
	
	@Autowired
	public AuthorizationServerConfig(
			AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder,
			DataSource dataSource,
			UserDetailsService userDetailsService,
			JwtAccessTokenConverter jwtAccessTokenConverter
			) {
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
		this.dataSource = dataSource;
		this.userDetailsService = userDetailsService;
		this.jwtAccessTokenConverter = jwtAccessTokenConverter;
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		clients
			.jdbc(dataSource)
			.passwordEncoder(passwordEncoder);
	}
	
	@Override
	public void configure (AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
	          .authenticationManager(authenticationManager)        
	          .tokenStore(tokenStore())
	          .approvalStore(new JdbcApprovalStore(dataSource))
			  .userDetailsService(userDetailsService)
			  .accessTokenConverter(jwtAccessTokenConverter);
	}
	
	@Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.passwordEncoder(passwordEncoder);
    }
	
	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}
	
}
