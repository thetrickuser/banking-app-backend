package com.geminibank.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geminibank.authservice.entity.UserCredential;
import com.geminibank.authservice.model.UserInfo;
import com.geminibank.authservice.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService service;
	
	@PostMapping("/register")
	public ResponseEntity<UserInfo> addNewUser(@RequestBody UserInfo userRequest) {
		UserInfo response = service.registerUser(userRequest);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/getToken")
	public String getToken(UserCredential userCredential) {
		return service.generateToken(userCredential.getUsername());
	}
	
	@GetMapping("/validateToken")
	public void validateToken(@RequestParam(value = "token", required = true) String token) {
		service.validateToken(token);
	}
	
}
