package com.cognizant.customerstatement.service;

import java.util.HashMap;
import java.util.List;

import com.cognizant.customerstatement.exception.InvalidJsonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.cognizant.customerstatement.model.CustomResponseBody;
import com.cognizant.customerstatement.model.CustomerStatement;
import com.cognizant.customerstatement.util.Helper;
import com.cognizant.customerstatement.util.Validator;

@Slf4j
@Service
public class StatementService {


	public CustomResponseBody validationService(List<CustomerStatement> customerStatementList) {
			log.info("Entering into StatementService <validationService>");
			boolean referenceStatus = true;
			boolean balanceStatus = true;
			HashMap<Long, String> errorRecordMap = new HashMap<Long, String>();
			HashMap<Long, CustomerStatement> statementMap = new HashMap<Long, CustomerStatement>();
			for (CustomerStatement statementData : customerStatementList) {
				if (!Validator.validateJson(statementData) || !Validator.typeCheck(statementData)) {
					throw new InvalidJsonException("Json is Invalid");
				}
				if (!Validator.validateBalance(statementData)) {
					balanceStatus = false;
					if (!errorRecordMap.containsKey(statementData.getReference())) {
						errorRecordMap.put(statementData.getReference(), statementData.getAccountNumber());
					}
				}
				if (statementMap.containsKey(statementData.getReference())) {
					if (!errorRecordMap.containsKey(statementData.getReference())) {
						errorRecordMap.put(statementData.getReference(), statementData.getAccountNumber());
					}
					referenceStatus = false;
				} else {
					statementMap.put(statementData.getReference(), statementData);
				}
			}
			log.info("Exiting from StatementService <validationService>");
			return Validator.responseData(balanceStatus, referenceStatus, Helper.listOfError(errorRecordMap));

		}
	}


