package com.insset.ccm.kevincardon.myreadingbooksback.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;

@Configuration
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    private long validityInMilliseconds = 7200000; // 2h

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth", Collections.singletonList(new SimpleGrantedAuthority(Role.ROLE_CLIENT.getAuthority())));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String resolveToken(String tokenJWT) {
            return tokenJWT.substring(7);
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String getAuthorization(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("auth").toString();
    }
}
