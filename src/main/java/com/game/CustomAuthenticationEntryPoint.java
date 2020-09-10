package com.game;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setStatus(403);
        try {
            httpServletResponse.getWriter().write(
                    new JSONObject()
                            .put("timestamp", LocalDateTime.now())
                            .put("status", 403)
                            .put("message", "Access denied")
                            .toString()
            );
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
}
