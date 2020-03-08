package com.cognizant.customerstatement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ErrorRecord {

	private Long reference;

	private String accountNumber;

	public ErrorRecord(Long reference, String accountNumber) {
		this.reference = reference;
		this.accountNumber = accountNumber;
	}
}
