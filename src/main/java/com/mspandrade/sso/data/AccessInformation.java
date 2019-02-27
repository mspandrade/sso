package com.mspandrade.sso.data;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class AccessInformation {

	private String username;
	private Set<String> authorities = new HashSet<>();
	
}
