package ru.sbrf.ku.library.servlets;

import ru.sbrf.ku.library.dao.BookDao;
import ru.sbrf.ku.library.dao.impl.BookDaoImpl;
import ru.sbrf.ku.library.entities.Book;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class BookServlet extends HttpServlet {
    String page = "<!DOCTYPE html>\n" +
            "<head>\n" +
            "<title>BookServlet Page</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<h1>%%PLACEHOLDER1%%</h1>\n" +
            "%%PLACEHOLDER2%%\n" +
            "</body>\n" +
            "</html>";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        BookDao bookDao = new BookDaoImpl();
//
//        List<Book> bookList = bookDao.getListByISBN(request.getPathInfo().replace("/",""));
//        StringBuilder sb = new StringBuilder();
//
//        if (request.isUserInRole("client")) {
//            boolean isInStock = false;
//            for (Book book : bookList){
//                if (book.getMovements().size() != 0 && bookDao.getLastMovement(book).getTo().getType().equals(1)){
//                    isInStock = true;
//                }
//            }
//            sb.append(isInStock == true? "Книга есть в наличии": "Все экземпляры на руках");
//        } else if (request.isUserInRole("librarian")) {
//            bookList.forEach(book -> sb
//                    .append("<p>")
//                    .append(book.getId())
//                    .append(" => ")
//                    .append(
//                            bookDao.getLastMovement(book) != null ?
//                                    bookDao.getLastMovement(book).getTo().getName():
//                                    "Not set yet"
//                    )
//                    .append("</p>"));
//        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter printWriter = response.getWriter();
//        printWriter.print(page
//                .replace("%%PLACEHOLDER1%%", bookList.get(0).getDescription().getName())
//                .replace("%%PLACEHOLDER2%%", sb.toString())
//        );
        printWriter.flush();
    }
}
