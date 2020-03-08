package com.cognizant.customerstatement.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import com.cognizant.customerstatement.model.CustomResponseBody;
import com.cognizant.customerstatement.model.CustomerStatement;
import com.cognizant.customerstatement.model.ErrorRecord;

@Slf4j
public class Validator {


	public static boolean validateJson(CustomerStatement statement) {
		log.info("Entering into Validator <validateJson> " + statement.toString());
		if (statement.getAccountNumber() == null || statement.getDescription() == null
				|| statement.getEndBalance() == null || statement.getMutation() == null
				|| statement.getStartBalance() == null) {
			log.info("Exiting from Validator <validateJson> " + false);
			return false;
		}
		log.info("Exiting from Validator <validateJson> " + true);
		return true;
	}

	public static boolean typeCheck(CustomerStatement statement) {
		log.info("Entering into Validator <typeCheck> " + statement.toString());
		try {
			Double.parseDouble(statement.getMutation());
			log.info("Exiting from Validator <typeCheck> " + true);
			return true;
		} catch (Exception err) {
			log.info("Exiting from Validator <typeCheck> " + false);
			return false;
		}
	}

	public static boolean validateBalance(CustomerStatement statement) {

		log.info("Entering into Validator <validateBalance> " );
		int maxPrecision = Math.max(statement.getStartBalance().toString().length(), statement.getMutation().length());
		Double value = statement.getStartBalance() + Double.parseDouble(statement.getMutation());
		BigDecimal data = new BigDecimal(value,
				new MathContext(Math.max(maxPrecision, statement.getEndBalance().toString().length())));
		BigDecimal result = new BigDecimal(statement.getEndBalance(),
				new MathContext(Math.max(statement.getEndBalance().toString().length(), maxPrecision)));
		log.info("Exiting from Validator <validateBalance>");
		return data.equals(result) ? true : false;
	}

	public static CustomResponseBody responseData(boolean balanceStatus, boolean refernceStatus,
			List<ErrorRecord> listofErrorRecord) {
		log.info("Entering into Validator <validateOutput> " + balanceStatus + " " + refernceStatus + " "
				+ listofErrorRecord.toString());
		CustomResponseBody response = new CustomResponseBody();
		response.setErrorRecords(listofErrorRecord);
		if (balanceStatus && refernceStatus) {
			response.setResult("SUCCESSFUL");
		} else if (!refernceStatus && balanceStatus) {
			response.setResult("DUPLICATE_REFERENCE");
		} else if (!refernceStatus && !balanceStatus) {
			response.setResult("DUPLICATE_REFERENCE_INCORRECT_END_BALANCE");
		} else {
			response.setResult("INCORRECT_END_BALANCE");
		}
		log.info("Exiting from Validator <validateOutput>");
		return response;
	}

}
