package com.mspandrade.sso.service;

import java.util.Arrays;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mspandrade.sso.entity.UserEntity;
import com.mspandrade.sso.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public UserEntity findByUsername(@NotNull String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		
		UserEntity userEntity = findByUsername(username);
		
		if (userEntity == null) {
			throw new UsernameNotFoundException("User not found with name: " + username);	
		}
		
		return new User(
				userEntity.getUsername(), 
				userEntity.getPassword() == null ? "" : userEntity.getPassword(), 
				Arrays.asList(
						new SimpleGrantedAuthority(userEntity.getLoginType().name())
						)
				);
	}

}
