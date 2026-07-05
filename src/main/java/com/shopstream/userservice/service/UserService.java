package com.shopstream.userservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shopstream.userservice.auth.JwtUtil;
import com.shopstream.userservice.auth.PasswordEncoder;
import com.shopstream.userservice.dao.IDataService;
import com.shopstream.userservice.entity.AuthenticationResponse;
import com.shopstream.userservice.entity.CreateUserRequest;
import com.shopstream.userservice.entity.User;
import com.shopstream.userservice.entity.UserLoginRequest;

@Service
public class UserService {
	@Autowired
	IDataService dataService;
	
	@Autowired
	JwtUtil tokengenarater;

	public User registerNewuser(CreateUserRequest request) {

		User user = new User();
		user.setFullName(request.getFull_name());
		user.setEmail(request.getEmail());
		String encodedPassword = PasswordEncoder.encode(request.getPassword());
		user.setPasswordHash(encodedPassword);
		user.setCountry(request.getCountry());
		Optional.ofNullable(request.getPhone()).ifPresent(user::setPhone);
		Optional.ofNullable(request.getDob()).ifPresent(user::setDob);
		Optional.ofNullable(request.getLanguage()).ifPresent(user::setLanguage);
		return dataService.save(user);

	}
	
	
	public AuthenticationResponse loginUser(UserLoginRequest loginRequest) throws UsernameNotFoundException {
		
		User user = dataService.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Invalid UserName or Password"));
	    String hashedPassword = user.getPasswordHash();
	    if(!PasswordEncoder.match(loginRequest.getPassword(), hashedPassword)) {
	    	throw new UsernameNotFoundException("Invalid UserName or Password");
	    }
	    
	    String token = tokengenarater.generateJWT(loginRequest.getEmail());
	    
	    return new AuthenticationResponse(token);
	}
}
