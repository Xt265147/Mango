package com.example.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

public class JwtTokenUtils {

    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String SECRET_KEY = "mango-jwt-secret-key-must-be-at-least-256-bits-long!";

    public static Authentication getAuthenticationFromToken(HttpServletRequest request) {
        String token = getToken(request);
        if (token == null) {
            return null;
        }
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String username = claims.getSubject();
            if (username == null) {
                return null;
            }
            WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            if (ctx != null) {
                UserDetailsService userDetailsService = ctx.getBean(UserDetailsService.class);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return authentication;
            }
        } catch (ExpiredJwtException e) {
            return null;
        }
        return null;
    }

    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);
        if (token == null) {
            token = request.getHeader("token");
        } else if (token.startsWith(TOKEN_PREFIX)) {
            token = token.substring(TOKEN_PREFIX.length());
        }
        if ("".equals(token)) {
            token = null;
        }
        return token;
    }
}
