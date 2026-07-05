package com.shopstream.userservice.auth;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class PasswordEncoder {
	// saltLength=16, hashLength=32, parallelism=1, memory=65536 (64MB), iterations=3
	private static final Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(16,32,1,65536,3);
	
	public static String encode(String rawPassword) {
		return encoder.encode(rawPassword);
	}

	public static boolean match(String rawPassword , String encodedPassword) {
		return encoder.matches(rawPassword, encodedPassword);
	}
}
