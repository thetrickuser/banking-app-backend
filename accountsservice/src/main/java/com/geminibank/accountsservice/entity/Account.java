package com.geminibank.accountsservice.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long accountNumber;
	
	@Column(nullable = false)
	private Double availableBalance;
	
	@Column(nullable = false)
	private Date accountCreationDate;
	
	@Column(nullable = false)
	private String accountType;

}
