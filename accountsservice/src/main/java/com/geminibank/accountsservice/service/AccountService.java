package com.geminibank.accountsservice.service;

import java.util.List;

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
			log.info("{}", response.toString());
			availableBalance = response.getAvailableBalance();
			log.info("{}", availableBalance);
		} catch(Exception e) {
			log.error("Error in fetching availableBalance. {}", e.getMessage());
		}
		
		log.info("{}", availableBalance);
		
		return availableBalance;
	}

	public List<Account> fetchAllAccounts(Long customerId, String token) {
		return repository.findByCustomerId(customerId);		
	}

}
