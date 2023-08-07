package com.geminibank.apigateway.util;

import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private static final String SECRET = "28574395JRHKFSDK43975RKJFM234799238NDM23974KJFDS@";
	
	public void validateToken(final String token) {
		Jwts.parserBuilder()
			.setSigningKey(getSignKey())
			.build()
			.parseClaimsJws(token);
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
