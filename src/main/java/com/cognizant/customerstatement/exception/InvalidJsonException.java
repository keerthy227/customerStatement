package com.cognizant.customerstatement.exception;


public class InvalidJsonException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new user name already exist exception.
	 *
	 * @param msg the msg
	 */
	public InvalidJsonException(String msg) {
		super(msg);
	}
}
