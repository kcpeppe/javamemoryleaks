package com.kodewerk.web;

import jakarta.servlet.http.HttpServlet;

public class HistoryServlet extends HttpServlet {

    public String getHeader() {
        return "<html><header><link rel=\"stylesheet\" href=\"/lab/css/plain.css\" type=\"text/css\" media=\"all\"/><TITLE>JPTWML - Stock History</TITLE></header>";
    }
}
