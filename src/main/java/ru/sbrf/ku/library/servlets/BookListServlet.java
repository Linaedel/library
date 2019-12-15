package ru.sbrf.ku.library.servlets;

import ru.sbrf.ku.library.dao.BookDao;
import ru.sbrf.ku.library.dao.impl.BookDaoImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BookListServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page = "<!DOCTYPE html>\n" +
                "<head>\n" +
                "<title>Index Page</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Список книг</h1>\n" +
                "%%PLACEHOLDER%%\n" +
                "</body>\n" +
                "</html>";
        BookDao bookDao = new BookDaoImpl();
        StringBuilder sb = new StringBuilder();

        bookDao.listOfBookNames().forEach(book -> sb.append("<p><a href='/book/").append(bookDao.getISBNbyBookName(book)).append("'>").append(book).append("</a></p>"));

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter printWriter = response.getWriter();
        printWriter.print( page.replace("%%PLACEHOLDER%%",sb.toString()));
        printWriter.flush();
    }
}
