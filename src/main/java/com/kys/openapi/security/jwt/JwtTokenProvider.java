package com.kys.openapi.security.jwt;

import com.kys.openapi.security.exception.InvalidJwtAuthenticationException;
import io.jsonwebtoken.*;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtTokenProvider {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    public static final String BEARER_ = "Bearer ";

    @Setter
    private String secret;

    private UserDetailsService userDetailsService;

    public JwtTokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String createToken(String username, List<String> roles) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);

        Date now = new Date();
        Date validity = new Date(now.getTime() + JWT_TOKEN_VALIDITY);

        return Jwts.builder()
                   .setClaims(claims)
                   .setIssuedAt(now)
                   .setExpiration(validity)
                   .signWith(SignatureAlgorithm.HS256, secret)
                   .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser()
                   .setSigningKey(secret)
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
    }

    public String resolveToken(HttpServletRequest req) {

        String bearerToken = req.getHeader(HttpHeaders.AUTHORIZATION);

        if (Objects.nonNull(bearerToken) && bearerToken.startsWith(BEARER_)) {
            return bearerToken.substring(7, bearerToken.length());
        }

        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                                     .setSigningKey(secret)
                                     .parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }
    }

}