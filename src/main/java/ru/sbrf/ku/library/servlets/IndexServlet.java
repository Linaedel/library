package ru.sbrf.ku.library.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class IndexServlet extends HttpServlet {
private static final String page = "<!DOCTYPE html>\n" +
        "<head>\n" +
        "<title>Index Page</title>\n" +
        "</head>\n" +
        "<body>\n" +
        "<h1>Библиотека</h1>\n" +
        "<p>Привет! Я - библиотека. Странно, да?</p>\n" +
        "</body>\n" +
        "</html>";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter printWriter = response.getWriter();
        printWriter.print(page);
        printWriter.flush();
    }
}
