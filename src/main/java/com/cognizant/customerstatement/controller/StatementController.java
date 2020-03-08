package com.cognizant.customerstatement.controller;

import java.util.List;

import com.cognizant.customerstatement.exception.InvalidJsonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.customerstatement.model.CustomResponseBody;
import com.cognizant.customerstatement.model.CustomerStatement;
import com.cognizant.customerstatement.service.StatementService;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/customerstatement")
public class StatementController {

	@Autowired
	private StatementService statementService;

	@PostMapping("/validate")
	public CustomResponseBody validateAllStatement(@RequestBody List<CustomerStatement> customerStatementList) {
		log.info("Entered into StatementController < validateAllStatement>");
		if(customerStatementList.isEmpty())
			throw new InvalidJsonException("BAD_REQUEST");
		CustomResponseBody response = statementService.validationService(customerStatementList);
		log.info("Exiting from StatementController < validateAllStatement>");
		return response;
	}

}
