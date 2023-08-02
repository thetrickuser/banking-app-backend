package com.geminibank.accountsservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geminibank.accountsservice.entity.Account;
import com.geminibank.accountsservice.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository repository;
	
	private static final Logger log = LoggerFactory.getLogger(AccountService.class); 
	
	public Account createAccount(Account account) {
		Account response = null;
		try {
			response = repository.save(account);			
		} catch(Exception e) {
			log.error("Error in creating account. {}", e.getMessage());
		}
		
		return response;
	}
	
	public Double getAvailableBalance(Long accountNumber) {
		Double availableBalance = null;
		try {
			Account response = repository.findByAccountNumber(accountNumber);
			availableBalance = response.getAvailableBalance();
		} catch(Exception e) {
			log.error("Error in fetching availableBalance. {}", e.getMessage());
		}
		
		return availableBalance;
	}

}
