package com.example.springbootstarter.Services;

import com.example.springbootstarter.Models.User;
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
public class JwtService {

    @Value("${security.jwt.issuer}")
    private String Issuer;

    @Value("${security.jwt.audience}")
    private String Audience;

    @Value("${security.jwt.secretKey}")
    private String SecretKey;

    public String generateToken (User user){
        byte[] keyBytes = Decoders.BASE64.decode(SecretKey);
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .setIssuer(Issuer)
                .setAudience(Audience)
                .claim("UserId", user.getId())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plusSeconds(3600)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
}