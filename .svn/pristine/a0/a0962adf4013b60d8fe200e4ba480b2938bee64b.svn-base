package com.bonus.core.jwt;

import io.jsonwebtoken.Claims;  
import io.jsonwebtoken.Jwts;  
import io.jsonwebtoken.SignatureAlgorithm;  
  
import java.util.Date;  
  
public class JwtUtil {  
  
    private String secret = "Bns*2024!";  
  
    private long expiration = 24 * 60 * 60;
  
    public String generateToken(String loginName) {  
        Date now = new Date();  
        Date expirationDate = new Date(now.getTime() + expiration * 1000);  
  
        return Jwts.builder()  
                .setSubject(loginName)  
                .setIssuedAt(now)  
                .setExpiration(expirationDate)  
                .signWith(SignatureAlgorithm.HS256, secret)  
                .compact();  
    }  
  
    public Claims parseToken(String token) {  
        try {  
            return Jwts.parser()  
                    .setSigningKey(secret)  
                    .parseClaimsJws(token)  
                    .getBody();  
        } catch (Exception e) {  
            return null;  
        }  
    }  
}