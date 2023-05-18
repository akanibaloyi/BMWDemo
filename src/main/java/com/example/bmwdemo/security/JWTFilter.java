package com.example.bmwdemo.security;

import com.example.bmwdemo.dao.UserDAO;
import com.example.bmwdemo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    JWTUtil jwtUtil;
    @Autowired
    UserDAO userDAO;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null){
            String token = authHeader.substring(7);
            String username = jwtUtil.extractUsername(token);
            User user = userDAO.retrieveUser(username);
            if(jwtUtil.validateToken(token, user)){
                UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), jwtUtil.getDefaultRoles());
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            filterChain.doFilter(request, response);
        }
    }
}
