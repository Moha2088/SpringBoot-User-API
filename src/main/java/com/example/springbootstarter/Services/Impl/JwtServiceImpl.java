package com.example.springbootstarter.Services.Impl;

import java.time.Instant;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.springbootstarter.Models.User;
import com.example.springbootstarter.Services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

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

    @Override
    public String extractToken(HttpServletRequest request) {
        String headerValue = request.getHeader("Authorization");
        return !headerValue.isEmpty() && headerValue.startsWith("Bearer")
                ? headerValue.substring(7)
                : null;
    }

    @Override
    public Boolean validateToken(String token) {
        Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
        return true;
    }
}