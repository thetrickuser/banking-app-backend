package com.geminibank.profileservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geminibank.profileservice.entity.Customer;
import com.geminibank.profileservice.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repository;
	
	private static final Logger log = LoggerFactory.getLogger(CustomerService.class); 
	
	public Customer createCustomerProfile(Customer customer) {
		Customer response = null;
		try {
			response = repository.save(customer);			
		} catch(Exception e) {
			log.error("Error in creating customer profile. {}", e.getMessage());
		}
		
		return response;
	}
	
	public Customer getCustomerProfile(String email) {
		Customer response = null;
		try {
			response = repository.findByEmail(email);			
		} catch(Exception e) {
			log.error("Error in fetching customer profile. {}", e.getMessage());
		}
		
		return response;
	}

}
