package com.kys.openapi.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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

    public static final long DEFAULT = 1000 * 60 * 60;
    public static final String BEARER_ = "Bearer ";

    @Setter
    private String secret;

    @Setter
    private Long validity = DEFAULT;

    private UserDetailsService userDetailsService;

    public JwtTokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String createToken(String username, List<String> roles) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);

        Date now = new Date();
        Date validity = new Date(now.getTime() + this.validity);

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

    /**
     * 토큰에서 userNo 확인
     * @param token
     * @return
     */
    public String getUsername(String token) {
        return Jwts.parser()
                   .setSigningKey(secret)
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
    }

    /**
     * 토큰 정보 확인
     * @param req
     * @return
     */
    public String resolveToken(HttpServletRequest req) {

        String bearerToken = req.getHeader(HttpHeaders.AUTHORIZATION);

        if (Objects.nonNull(bearerToken) && bearerToken.startsWith(BEARER_)) {
            return bearerToken.substring(7, bearerToken.length());
        }

        return null;
    }

    /**
     * 토큰 검증
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        Jws<Claims> claims = Jwts.parser()
                                 .setSigningKey(secret)
                                 .parseClaimsJws(token);

        if (claims.getBody().getExpiration().before(new Date())) {
            return false;
        }

        return true;
    }

}