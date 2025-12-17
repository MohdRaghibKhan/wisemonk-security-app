package com.wisemonksecurity.security;

import java.util.Base64;
import java.util.Date;
import java.util.Set;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private String secretKey =  "";
    public JwtUtil() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = keyGen.generateKey();
			 secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
    public String generateToken(String email, Set<String> roles) {
        return Jwts.builder()
                .setSubject(email)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}
