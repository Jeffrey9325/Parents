package com.everis.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.everis.model.Parents;

@ControllerAdvice
public class ApiRequestHandler {
	
	@ExceptionHandler(value = {ApiRequestException.class})
	public ResponseEntity<Object> handleApiRequestxception(ApiRequestException e){
		//public ResponseEntity<Object> handleApiRequestxception(ApiRequestException e){
		
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		ApiException apiException = new ApiException(
				e.getMessage(),
				badRequest,
				ZonedDateTime.now(ZoneId.of("Z"))
	     );
		return new ResponseEntity<>(apiException, badRequest);
	}
	
}
