package com.madhurika.reliableOrderService.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationMs;

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String generateToken(String username){
        return Jwts.builder().subject(username).issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+expirationMs))
                .signWith(getSigningKey()).compact();
    }

    public String extractUsername(String token){
        return Jwts.parser().verifyWith(getSigningKey()).build()
                .parseSignedClaims(token).getPayload().getSubject();
    }

    private boolean isTokenExpired(String token){
        return Jwts.parser().verifyWith(getSigningKey()).build()
                .parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public boolean isTokenValid(String token, String username){
        try{
            String extracted = extractUsername(token);
            return extracted.equals(username) && !isTokenExpired(token);
        } catch (Exception e){
            return false;
        }
    }

    
}
