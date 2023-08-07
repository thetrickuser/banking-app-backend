package com.geminibank.authservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.geminibank.authservice.entity.UserCredential;
import com.geminibank.authservice.model.CustomUserDetails;
import com.geminibank.authservice.repository.UserCredentialRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserCredentialRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserCredential> credential = repository.findByUsername(username);
		return credential
				.map(CustomUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

}
