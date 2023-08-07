package com.geminibank.authservice.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	private Long accountNumber;
	private Double availableBalance;
	private String accountType;
	private Date accountCreationDate;
	private Long customerId;
	
	public Account(Double availableBalance, String accountType, Date accountCreationDate) {
		super();
		this.availableBalance = availableBalance;
		this.accountType = accountType;
		this.accountCreationDate = accountCreationDate;
	}
	
	
}
