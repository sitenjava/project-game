package com.game;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.game.data.entities.User;
import com.game.data.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Read the Authorization header (JWT token)
        String header = request.getHeader(JwtProperties.JWT_HEADER_STRING);

        if (header == null || !header.startsWith(JwtProperties.JWT_TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        // Grab user principal from database and perform authorization
        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JwtProperties.JWT_HEADER_STRING);

        // Validate token
        String username = JWT.require(Algorithm.HMAC512(JwtProperties.JWT_SECRET.getBytes()))
                .build()
                .verify(token.replace(JwtProperties.JWT_TOKEN_PREFIX, ""))
                .getSubject();

        if (username != null) {
            User user = userRepository.findUserByUsername(username);
            CustomUserDetails userDetails = new CustomUserDetails(user);
            return new UsernamePasswordAuthenticationToken(
                    username, null, userDetails.getAuthorities()
            );
        }

        return null;
    }
}
