package com.geminibank.accountsservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geminibank.accountsservice.entity.Account;
import com.geminibank.accountsservice.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	private static final Logger log = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountService service;
	
	@PostMapping("/createAccount")
	public ResponseEntity<Account> createAccount(@RequestBody Account accountRequest) {
		Account response = null;
		try {
			response = service.createAccount(accountRequest);
		} catch(Exception e) {
			log.error("Exception {}", e.getMessage());
		}
		
		return new ResponseEntity<Account>(response, HttpStatus.OK);
	}
	
	@GetMapping("/fetchBalance/{accountNumber}")
	public ResponseEntity<Double> getAvailableBalance(@PathVariable("accountNumber") Integer accountNumber) {
		Double availableBalance = service.getAvailableBalance(accountNumber.longValue());
		log.info("{}",availableBalance);
		return new ResponseEntity<Double>(availableBalance, HttpStatus.OK);
	}

}
