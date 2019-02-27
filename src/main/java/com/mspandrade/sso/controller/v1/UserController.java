package com.mspandrade.sso.controller.v1;

import java.security.Principal;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mspandrade.sso.data.UserDTO;
import com.mspandrade.sso.entity.UserEntity;
import com.mspandrade.sso.service.UserService;

@RestController
@RequestMapping("v1/users")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("current")
	public UserDTO current(Principal principal) {	
		
		UserEntity userEntity = userService.findByUsername(principal.getName());
		
		if (userEntity == null) {
			throw new UsernameNotFoundException("User not found with name: " + principal.getName());	
		}
		
		return new UserDTO(
				userEntity.getId(),
				userEntity.getUsername(), 
				userEntity.getLoginType().name()
				);
	}
	
}
