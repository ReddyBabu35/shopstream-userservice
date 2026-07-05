package com.shopstream.userservice.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) {
		//authenticated will give you 403 - forbidden
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/signin", "/login").permitAll()
				.anyRequest().authenticated())
		//Disable CSRF as we user JWT. It related to session security
		.csrf(csrf -> csrf.disable())
		
		//Making session stateless, Bydefault session will be statefull
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return http.build();
		
	}

}
