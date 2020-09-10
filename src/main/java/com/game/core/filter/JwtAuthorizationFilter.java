package com.game.core.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.game.CustomUserDetails;
import com.game.common.SecurityConstants;
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

    private final UserRepository userRepository;

    private static String token;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Read the Authorization header (JWT token)
        String header = request.getHeader(SecurityConstants.JWT_HEADER_STRING);

        if (header == null || !header.startsWith(SecurityConstants.JWT_TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        // Grab user principal from database and perform authorization
        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        token = request.getHeader(SecurityConstants.JWT_HEADER_STRING);

        // Validate token
        String username = getUsernameFromToken(token);

        if (username != null) {
            User user = userRepository.findUserByUsername(username);
            CustomUserDetails userDetails = new CustomUserDetails(user);
            return new UsernamePasswordAuthenticationToken(
                    username, null, userDetails.getAuthorities()
            );
        }
        return null;
    }

    private String getUsernameFromToken(String token) {
        return JWT.require(Algorithm.HMAC512(SecurityConstants.JWT_SECRET.getBytes()))
                .build()
                .verify(token.replace(SecurityConstants.JWT_TOKEN_PREFIX, ""))
                .getSubject();
    }

    public User getUserFromToken() {
        return userRepository.findUserByUsername(getUsernameFromToken(token));
    }
}
