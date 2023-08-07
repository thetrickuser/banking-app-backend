package com.geminibank.accountsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geminibank.accountsservice.entity.Account;
import java.util.List;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	public Account findByAccountNumber(Long accountNumber);
	public List<Account> findByCustomerId(Long customerId);
}
