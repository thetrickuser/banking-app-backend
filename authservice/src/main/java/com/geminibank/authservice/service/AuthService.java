package com.geminibank.authservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import com.geminibank.authservice.entity.UserCredential;
import com.geminibank.authservice.model.UserInfo;
import com.geminibank.authservice.repository.UserCredentialRepository;
import com.geminibank.authservice.util.JwtUtil;
import com.geminibank.authservice.model.*;

@Service
public class AuthService {

	@Autowired
	private UserCredentialRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Value("${accountServiceUrl}")
	String accountServiceUrl;
	
	@Value("${profileServiceUrl}")
	String profileServiceUrl;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	private static final Logger log = LoggerFactory.getLogger(AuthService.class);
	
	
	public UserInfo registerUser(UserInfo userRequest) {
		
		Account accountRequest = new Account(
				userRequest.getAvailableBalance(),
				userRequest.getAccountType(),
				new Date());
		
		CustomerProfile profileRequest = new CustomerProfile(
				userRequest.getName(), 
				userRequest.getPhoneNumber(),
				userRequest.getAddress(),
				userRequest.getEmail());
		
		UserCredential credential = new UserCredential(
				userRequest.getUsername(),
				userRequest.getPassword());
				
		
		RestTemplate template = new RestTemplate();
		log.info(accountServiceUrl);
		log.info(profileServiceUrl);
		Account accountResponse = 
				template.postForObject(accountServiceUrl, accountRequest, Account.class);
		CustomerProfile profileResponse = 
				template.postForObject(profileServiceUrl, profileRequest, CustomerProfile.class);
		saveUser(credential);
		
		UserInfo response = new UserInfo();
		response.setAccountNumber(accountResponse.getAccountNumber());
		response.setAccountType(accountResponse.getAccountType());
		response.setAvailableBalance(accountResponse.getAvailableBalance());
		response.setCustomerId(profileResponse.getCustomerId());
		response.setUsername(credential.getUsername());
		response.setPassword(credential.getPassword());
		
		
		return response;
		
	}
	
	private UserCredential saveUser(UserCredential credential) {
		credential.setPassword(passwordEncoder.encode(credential.getPassword()));
		return repository.save(credential);
	}
	
	public String generateToken(String username) {
		return jwtUtil.generateToken(username);
	}
	
	public void validateToken(String token) {
		jwtUtil.validateToken(token);
	}
	
	
}
