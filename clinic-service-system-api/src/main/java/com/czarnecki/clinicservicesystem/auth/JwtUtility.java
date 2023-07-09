package com.czarnecki.clinicservicesystem.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
class JwtUtility {

  @Value("${app.jwtSecretKey}")
  private String jwtSecretKey;

  @Value("${app.jwtExpirationInMs}")
  private int jwtExpirationInMs;

  String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, userDetails.getUsername());
  }

  private String createToken(Map<String, Object> claims, String subject) {
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
        .signWith(SignatureAlgorithm.HS256, jwtSecretKey)
        .compact();
  }

  Boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

}
