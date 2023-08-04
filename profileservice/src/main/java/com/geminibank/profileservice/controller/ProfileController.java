package com.geminibank.profileservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geminibank.profileservice.entity.Customer;
import com.geminibank.profileservice.service.CustomerService;

@RestController()
@RequestMapping("/profile")
public class ProfileController {
	
private static final Logger log = LoggerFactory.getLogger(ProfileController.class);
	
	@Autowired
	private CustomerService service;
	
	@PostMapping("/createCustomerProfile")
	public ResponseEntity<Customer> createCustomerProfile(@RequestBody Customer customerRequest) {
		Customer response = null;
		try {
			response = service.createCustomerProfile(customerRequest);
		} catch(Exception e) {
			log.error("Exception {}", e.getMessage());
		}
		
		return new ResponseEntity<Customer>(response, HttpStatus.OK);
	}

}
