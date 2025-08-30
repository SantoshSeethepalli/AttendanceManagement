package com.santosh.SpringSecurity.Authentication.JwtServices;

import com.santosh.SpringSecurity.Authentication.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ApplicationContext appContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        try {

            String token = authorizationHeader.substring(7);
            String userIdFromToken = jwtService.extractUserId(token);

            if (userIdFromToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                int userId = Integer.parseInt(userIdFromToken);

                UserDetails userDetails = appContext.getBean(MyUserDetailsService.class).loadUserByUserId(userId);

                if (jwtService.isTokenValid(token, userDetails)) {

                    String role = jwtService.extractRole(token);

                    var authorities = Collections.singletonList(
                            new SimpleGrantedAuthority("ROLE_" + role)
                    );

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);

        } catch (Exception exception) {

            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}
