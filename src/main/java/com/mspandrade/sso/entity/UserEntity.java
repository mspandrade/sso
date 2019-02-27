package com.mspandrade.sso.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.mspandrade.sso.enums.LoginType;

import lombok.Data;

@Entity(name = "users")
@Data
public class UserEntity {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(
			unique = true,
			nullable = false
			)
	private String username;
	
	@Column(nullable = true)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private LoginType loginType;
	
}
