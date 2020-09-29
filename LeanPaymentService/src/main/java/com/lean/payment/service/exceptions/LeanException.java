package com.lean.payment.service.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Qais Azzeh
 *
 */
public class LeanException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private String responseMessage;

	@Getter
	@Setter
	private String responseCode;

	public LeanException() {
	}

	public LeanException(String message, String responseCode, Throwable throwable) {
		super(message, throwable);
		this.responseMessage = message;
		this.responseCode = responseCode;
	}

	public LeanException(String message, String responseCode) {
		super(message);
		this.responseMessage = message;
		this.responseCode = responseCode;
	}

}
