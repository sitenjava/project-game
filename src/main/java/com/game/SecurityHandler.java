package com.game;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        boolean admin = false;
        for (GrantedAuthority authority: authentication.getAuthorities()) {
            if (authority.getAuthority().equals("ADMIN")) {
                admin = true;
                break;
            }
        }

        if (admin) {
            httpServletResponse.sendRedirect("/admin/index");
        } else {
            httpServletResponse.sendRedirect("/index");
        }
    }
}
