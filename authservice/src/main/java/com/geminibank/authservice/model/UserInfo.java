package com.geminibank.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
	
	private Long customerId;
	private String name;
	private String phoneNumber;
	private String address;
	private String email;
	private String password;
	private String username;
	private String token;

}
