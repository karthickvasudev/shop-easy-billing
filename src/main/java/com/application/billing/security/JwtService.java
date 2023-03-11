package com.application.billing.security;

import com.application.billing.api.v1.user.User;
import com.application.billing.api.v1.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
@AllArgsConstructor
@Slf4j
public class JwtService {
    private final String signingKey = "472D4B6150645367566B59703273357638792F423F4528482B4D625165546857";
    private final UserRepository userRepository;

    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Key getSignInKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(signingKey));
    }

    public String generateToken(Map<String, Object> extras, User user) {
        return Jwts.builder()
                .setClaims(extras)
                .setSubject(user.getId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 1440))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token,String expectedUserId) {
        String userId = extractUserId(token);
        return userId.equals(expectedUserId) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractTokenExpiryDate(token).before(new Date());
    }

    private Date extractTokenExpiryDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
