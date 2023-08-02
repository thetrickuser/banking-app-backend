package com.geminibank.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.geminibank.auth.entity.Customer;
import com.geminibank.auth.service.UserService;

@RestController("/auth")
public class AuthController {
}
