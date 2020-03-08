package com.cognizant.customerstatement.exception;

import com.cognizant.customerstatement.model.CustomResponseBody;
import com.cognizant.customerstatement.model.ErrorRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Business exception.
	 *
	 * @param error the error
	 * @return the response entity
	 */


	@ExceptionHandler(InvalidJsonException.class)
	public ResponseEntity<CustomResponseBody> businessException(InvalidJsonException error) {
		log.info("Entering into ExceptionMapper<businessException>");
		log.error(error.getMessage());
		CustomResponseBody response = new CustomResponseBody();
		response.setResult("BAD_REQUEST");
		response.setErrorRecords(new ArrayList<ErrorRecord>());
		log.info("Exiting from ExceptionMapper<businessException>");
		return new ResponseEntity<CustomResponseBody>(response, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(HttpMessageConversionException.class)
	public ResponseEntity<CustomResponseBody> businessException(HttpMessageConversionException error) {
		log.info("Entering into ExceptionMapper<businessException>");
		log.error(error.getMessage());
		CustomResponseBody response = new CustomResponseBody();
		response.setResult("BAD_REQUEST");
		response.setErrorRecords(new ArrayList<ErrorRecord>());
		log.info("Exiting from ExceptionMapper<businessException>");
		return new ResponseEntity<CustomResponseBody>(response, HttpStatus.BAD_REQUEST);
	}

	/**
	 * System exception.
	 *
	 * @param error the error
	 * @return the response entity
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomResponseBody> systemException(Exception error) {
		log.info("Entering into ExceptionMapper<systemException>");
		log.error(error.getMessage());
		CustomResponseBody response = new CustomResponseBody();
		response.setResult("INTERNAL_SERVER_ERROR");
		response.setErrorRecords(new ArrayList<ErrorRecord>());
		log.info("Exiting from ExceptionMapper<systemException>");
		log.info(String.valueOf(error));
		return new ResponseEntity<CustomResponseBody>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
