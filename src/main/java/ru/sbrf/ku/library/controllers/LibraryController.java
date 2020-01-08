package ru.sbrf.ku.library.controllers;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sbrf.ku.library.dao.BookDao;

import java.sql.SQLException;

@Controller
public class LibraryController {
    @Autowired
    BookDao bookDao;

    @RequestMapping(value = "/")
    public String index(ModelMap modelMap) throws SQLException {
        modelMap.addAttribute("message","Hello, world!");
        return "jsp/index";
    }

    @RequestMapping(value = "/init")
    public String dbInit(ModelMap modelMap) throws SQLException {

        bookDao.list().forEach(book -> System.out.println(book.getDescription().getName()));

        return "jsp/index";
    }
}
