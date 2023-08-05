package com.geminibank.authservice.repository;

import org.springframework.stereotype.Repository;

import com.geminibank.authservice.entity.UserCredential;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {
	

}
