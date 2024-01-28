package com.czarnecki.clinicservicesystem.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
class JwtRequestFilter extends OncePerRequestFilter {
  private final UserDetailsService customUserDetailsService;
  private final JwtUtility jwtUtility;

  JwtRequestFilter(final UserDetailsService customUserDetailsService,
                   final JwtUtility jwtUtility) {
    this.customUserDetailsService = customUserDetailsService;
    this.jwtUtility = jwtUtility;
  }

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
                                  @NonNull HttpServletResponse response,
                                  @NonNull FilterChain filterChain) throws ServletException, IOException {
    final String authorizationHeader = request.getHeader("Authorization");

    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    final String jwt = authorizationHeader.substring(7);
    final String username = jwtUtility.extractUsername(jwt);

    if (SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
      if (Boolean.TRUE.equals(jwtUtility.isTokenValid(jwt, userDetails))) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(
            userDetails, new Object(), userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }
    filterChain.doFilter(request, response);
  }

}
