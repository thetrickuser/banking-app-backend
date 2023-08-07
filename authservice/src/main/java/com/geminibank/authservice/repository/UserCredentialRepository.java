package com.geminibank.authservice.repository;

import org.springframework.stereotype.Repository;

import com.geminibank.authservice.entity.UserCredential;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {

	Optional<UserCredential> findByUsername(String username);
	

}
