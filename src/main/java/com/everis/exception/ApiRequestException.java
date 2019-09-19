package com.everis.exception;

public class ApiRequestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1601250946551770279L;
	
	public ApiRequestException(String message) {
		super(message);
	}
	public ApiRequestException(String message, Throwable cause) {
		super(message, cause);
	}

}
