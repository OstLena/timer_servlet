package com.goit.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss O");
        String timezone = req.getParameter("timezone");
        String zoneId;
        if (timezone != null) {
            zoneId = timezone;
        } else {
            zoneId = "UTC";
        }
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of(zoneId));
        String currentTime = dtf.format(now);
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write(currentTime);
        resp.getWriter().close();
    }
}