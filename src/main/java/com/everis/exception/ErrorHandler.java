package com.everis.exception;

import java.util.List;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	   public ResponseEntity<ErrorInfo> methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
////	       ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), request.getRequestURI());
////	       return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
//		
//		// get spring errors
//        BindingResult result = e.getBindingResult();
//        List<FieldError> fieldErrors = result.getFieldErrors();
//
//        // convert errors to standard string
//        StringBuilder errorMessage = new StringBuilder();
//        fieldErrors.forEach(f -> errorMessage.append(f.getField() + " " + f.getDefaultMessage() +  " "));
//
//        // return error info object with standard json
//        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.toString(), errorMessage.toString(), request.getRequestURI());
//        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
//	   }
}