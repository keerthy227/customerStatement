package com.cognizant.customerstatement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerStatement {

	@JsonProperty(value = "Reference")
	private Long reference;

	@JsonProperty(value = "AccountNumber")
	private String accountNumber;

	@JsonProperty(value = "Description")
	private String description;

	@JsonProperty(value = "Start Balance")
	private Double startBalance;

	@JsonProperty(value = "Mutation")
	private String mutation;

	@JsonProperty(value = "End Balance")
	private Double endBalance;


}
