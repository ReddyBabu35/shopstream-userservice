package com.shopstream.userservice.auth;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private long expiration;

	public String generateJWT(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();

	}
	
	public String getEmail(String jwt) {
		
		return getClaimFromToken(jwt, Claims::getSubject);
	}
	
	private <T> T getClaimFromToken(String jwt, Function<Claims, T> claimResolver) {
		
		final Claims claims =  Jwts.parserBuilder()
									.setSigningKey(getSignInKey())
									.build()
									.parseClaimsJws(jwt)
									.getBody();
		return claimResolver.apply(claims);
		
	}
	/***
	 * This method is to compare whether the db email and the token email is same or not. Just to be defensive
	 * @param jwt
	 * @param email
	 * @return
	 */
	public boolean isTokenValid(String jwt, String email) {

		final String TokenEmail = getEmail(jwt);

		return (TokenEmail.equals(email) && !isTokenExpired(jwt));
	}

	private boolean isTokenExpired(String jwt) {
		return getClaimFromToken(jwt, Claims::getExpiration).before(new Date());

	}

	private Key getSignInKey() {
		// converting the string to raw bytes [for cryto algos to use
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		// hmacShaKeyFor() method will takes the raw input bytes and return the Key
		// object,
		// It will also checks the complexity of the key whether it is strong or not
		return Keys.hmacShaKeyFor(keyBytes);
	}

}