package com.example.machinesAndFailures.config;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Authorization extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        httpServletResponse.addHeader("Access-Control-Allow-Origin", ApplicationEnvironment.CLIENT_DOMAIN_URL);

        httpServletResponse.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, "
                + "Content-Type, Access-Control-Request-Method, " + "Access-Control-Request-Headers");

        httpServletResponse.addHeader("Access-Control-Expose-Headers",
                "Access-Control-Allow-Origin, " + "Access-Control-Allow-Credentials, " );

        httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET," + "POST, " + "DELETE");

        if ((httpServletRequest.getMethod().equalsIgnoreCase("OPTIONS"))) {
            try {
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
