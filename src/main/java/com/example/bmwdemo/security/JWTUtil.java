package com.example.bmwdemo.security;

import com.example.bmwdemo.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    private String createToken(Map<String, Object> claims, String username){
        return Jwts.builder().setClaims(claims).setSubject(username).
                   setIssuedAt(new Date(System.currentTimeMillis())).
                   setExpiration(new Date(System.currentTimeMillis() + 1000*60 * 60 * 60 * 5)).
                   signWith(SignatureAlgorithm.HS256, secret).compact();
    }
    
    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<String, Object>();
        return createToken(claims, username);
    }
    
    public boolean validateToken(String token, User user){
        String username = extractUsername(token);
        return username.equals(user.getUsername()) && ! isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    protected String extractUsername(String token) {
        return extractAllClaimsFromToken(token).getSubject();
    }

    protected Date extractExpiration(String token) {
        return extractAllClaimsFromToken(token).getExpiration();
    }

    private Claims extractAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    protected List<SimpleGrantedAuthority> getRolesFromToken(String authToken) {
        List<SimpleGrantedAuthority> roles = null;
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken).getBody();
        Boolean isAdmin = claims.get("isAdmin", Boolean.class);
        Boolean isUser = claims.get("isUser", Boolean.class);
        if (isAdmin != null && isAdmin == true) {
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        if (isUser != null && isUser == true) {
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return roles;
    }
}
