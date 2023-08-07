package com.geminibank.authservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geminibank.authservice.entity.UserCredential;
import com.geminibank.authservice.model.AuthRequest;
import com.geminibank.authservice.model.UserInfo;
import com.geminibank.authservice.service.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:3000"})
public class AuthController {

	@Autowired
	private AuthService service;

	@Autowired
	private AuthenticationManager authenticationManager;

	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/register")
	@CrossOrigin(origins = {"http://localhost:3000"})
	public ResponseEntity<UserInfo> addNewUser(@RequestBody UserInfo userRequest) {
		UserInfo response = service.registerUser(userRequest);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<UserInfo> login(@RequestBody AuthRequest authRequest) {
		log.info("starting authentication");
		UserInfo response = null;
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		if (authenticate.isAuthenticated()) {
			response = service.login(authRequest);
		}
		String token = "Bearer " + service.generateToken(authRequest.getUsername());

		log.info("Login success");
		return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.AUTHORIZATION, token).body(response);

	}

	@GetMapping("/validate")
	@CrossOrigin(origins = {"http://localhost:3000"})
	public void validateToken(@RequestParam(value = "token", required = true) String token) {
		service.validateToken(token);
	}

}
