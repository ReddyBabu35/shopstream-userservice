package com.shopstream.userservice.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopstream.userservice.entity.AuthenticationResponse;
import com.shopstream.userservice.entity.CreateUserRequest;
import com.shopstream.userservice.entity.User;
import com.shopstream.userservice.entity.UserLoginRequest;
import com.shopstream.userservice.service.UserService;

import jakarta.validation.Valid;

@RestController
public class Controler {
	@Autowired
	UserService service;

	@PostMapping("signin")
	public ResponseEntity<String> registerNewuser(@Valid @RequestBody CreateUserRequest user) {
		System.out.println(user);
		User saveduser = service.registerNewuser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("user creatd with " + saveduser.getUserId());
	}

	@PostMapping("login")
	public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody UserLoginRequest loginRequest) {
		System.out.print(loginRequest);
		AuthenticationResponse response = service.loginUser(loginRequest);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
