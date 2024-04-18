package com.chan.ssb.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CustomFilter2 implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String username = req.getParameter("username");

        if(username.equals("test")){
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "test");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
