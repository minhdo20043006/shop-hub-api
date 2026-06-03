package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.example.demo.entities.Account;

@Component
public class JwtUtil {

	private final SecretKey secretKey = Keys.hmacShaKeyFor(
			"my-very-long-secret-key-32+chars-minimum!".getBytes());

	public String generateAccessToken(Account account) {

		List<String> roles = account.getRoleAccounts().stream().map(ra -> ra.getRole().getNameRole()).toList();

		return Jwts.builder()
				.setSubject(account.getUsername())
				.claim("userId", account.getId())
				.claim("roles", roles)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 15 * 60 * 1000))
				.signWith(secretKey)
				.compact();
	}

	public Claims parseToken(String token) {
		return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
	}

	public String extractUsername(String token) {
		return parseToken(token).getSubject();
	}

	public Date extractExpiration(String token) {
		return parseToken(token).getExpiration();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public Boolean validateToken(String token, org.springframework.security.core.userdetails.UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}