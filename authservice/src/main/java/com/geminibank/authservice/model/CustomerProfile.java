package com.geminibank.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerProfile {
	private Long customerId;
	private String name;
	private String phoneNumber;
	private String address;
	private String email;
	
	public CustomerProfile(String name, String phoneNumber, String address, String email) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.email = email;
	}
	
	

}
