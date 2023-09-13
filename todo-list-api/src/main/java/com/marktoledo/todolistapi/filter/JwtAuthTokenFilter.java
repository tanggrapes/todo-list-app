package com.marktoledo.todolistapi.filter;

import com.marktoledo.todolistapi.security.UserDetails;
import com.marktoledo.todolistapi.security.UserDetailsServiceImpl;
import com.marktoledo.todolistapi.service.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthTokenFilter extends OncePerRequestFilter {

    private final String TOKEN_PREFIX = "Bearer";
    private JwtTokenService jwtTokenService;

    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    public JwtAuthTokenFilter(JwtTokenService jwtTokenService, UserDetailsServiceImpl userService){
        this.jwtTokenService = jwtTokenService;
        this.userDetailsService = userService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if(authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)){
            token = authorizationHeader.replace(TOKEN_PREFIX,"").trim();
            username = jwtTokenService.getUsernameInToken(token);
        }

        if(username != null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(jwtTokenService.isTokenValid(token, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request,response);
    }
}
