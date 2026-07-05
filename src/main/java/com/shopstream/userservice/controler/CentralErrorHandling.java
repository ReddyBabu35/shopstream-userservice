package com.shopstream.userservice.controler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.shopstream.userservice.entity.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CentralErrorHandling {

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFound(UsernameNotFoundException ex,HttpServletRequest request){
		
		ErrorResponse error = new ErrorResponse(
				HttpStatus.UNAUTHORIZED.value(), 
				ex.getMessage(),
				request.getRequestURI(),
				LocalDateTime.now()
				);
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
		
	}
}
