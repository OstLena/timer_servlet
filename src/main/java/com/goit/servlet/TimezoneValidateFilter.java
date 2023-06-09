package com.goit.servlet;

import jakarta.servlet.FilterChain;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.TimeZone;

import static java.util.Objects.isNull;

@WebFilter("/time/*")
public class TimezoneValidateFilter extends HttpFilter {


    protected void doFilter(HttpServletRequest req,
                            HttpServletResponse resp,
                            FilterChain chain) throws IOException {
        String timezone = req.getHeader("timezone");
        try {
            if (!isNull(timezone)) {
                TimeZone timeZone = TimeZone.getTimeZone(timezone);
                System.out.println("Valid time zone " + timeZone);
            }
            chain.doFilter(req, resp);
        } catch (Exception e) {
            resp.setStatus(400);
            resp.setContentType("text/html; charset=utf-8");
            resp.getWriter().write("Invalid timezone");
            resp.getWriter().close();
        }
    }
}
