package com.geminibank.authservice.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.geminibank.authservice.entity.UserCredential;
import com.geminibank.authservice.model.AuthRequest;
import com.geminibank.authservice.model.CustomerProfile;
import com.geminibank.authservice.model.UserInfo;
import com.geminibank.authservice.repository.UserCredentialRepository;
import com.geminibank.authservice.util.JwtUtil;

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

		RestTemplate template = new RestTemplate();
		CustomerProfile profileRequest = createProfileRequest(userRequest);
		CustomerProfile profileResponse = template.postForObject(profileServiceUrl, profileRequest,
				CustomerProfile.class);

		UserCredential credential = ceateCredentialsRequest(userRequest, profileResponse.getCustomerId());

		saveUser(credential);

		UserInfo response = new UserInfo();
		createRegistrationResponse(credential, profileResponse, response);

		return response;

	}

	public UserInfo login(AuthRequest credential) {
		/*
		 * Authentication manager authenticates using authentication provider bean as
		 * defined in AuthConfig class.
		 */
		UserInfo loginResponse = new UserInfo();
		Optional<UserCredential> response = repository.findByUsername(credential.getUsername());
		UserCredential userData = response.get();
		Long customerId = userData.getCustomerId();
		loginResponse.setCustomerId(customerId);
		return loginResponse;
	}

	private void createRegistrationResponse(UserCredential credential, CustomerProfile profileResponse,
			UserInfo response) {
		response.setCustomerId(profileResponse.getCustomerId());
		response.setUsername(credential.getUsername());
		response.setPassword(credential.getPassword());
	}

	private UserCredential ceateCredentialsRequest(UserInfo userRequest, Long customerId) {
		return new UserCredential(userRequest.getUsername(), userRequest.getPassword(), customerId);
	}

	private CustomerProfile createProfileRequest(UserInfo userRequest) {
		return new CustomerProfile(userRequest.getName(), userRequest.getPhoneNumber(), userRequest.getAddress(),
				userRequest.getEmail());
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
