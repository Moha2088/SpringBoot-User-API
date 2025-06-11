package com.example.springbootstarter.Services.Impl;

import com.example.springbootstarter.Models.User;
import com.example.springbootstarter.Services.JwtService;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@SuppressWarnings("deprecation")
@Service
public class JwtServiceImpl implements JwtService {

    @Value("${security.jwt.issuer}")
    private String Issuer;

    @Value("${security.jwt.audience}")
    private String Audience;

    @Value("${security.jwt.secretKey}")
    private String SecretKey;

    @Override
    public String generateToken(User user) {
        return Jwts.builder()
                .setIssuer(Issuer)
                .setAudience(Audience)
                .claim("UserId", user.getId())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plusSeconds(3600)))
                .signWith(SignatureAlgorithm.HS512, getSigningKey())
                .compact();
    }

    @Override
    public Claims getAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}