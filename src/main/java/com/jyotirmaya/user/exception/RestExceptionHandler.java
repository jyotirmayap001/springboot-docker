package com.jyotirmaya.user.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(value=UserNotfoundException.class)
	public ResponseEntity<ApiErrorResponse> handleNoUserFoundException() {
		
		ApiErrorResponse error=new ApiErrorResponse(400,"User not found",new Date());
		
		return new ResponseEntity<ApiErrorResponse>(error,HttpStatus.BAD_REQUEST);
	}
}
