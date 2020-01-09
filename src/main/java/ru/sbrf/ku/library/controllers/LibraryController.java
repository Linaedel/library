package ru.sbrf.ku.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sbrf.ku.library.dao.BookDao;
import ru.sbrf.ku.library.services.UserService;

@Controller
public class LibraryController {
    @Autowired
    BookDao bookDao;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/")
    public String index(ModelMap modelMap)  {
        modelMap.addAttribute("message","Hello, world!");
        return "index";
    }

    @RequestMapping(value = "/init")
    public String dbInit(ModelMap modelMap) {
        bookDao.list().forEach(book -> System.out.println(book.getDescription().getName()));
        return "index";
    }

    @RequestMapping(value = "/librarians", method = RequestMethod.GET)
    public String librarianList(ModelMap modelMap) {
        modelMap.addAttribute("librarians", userService.getLibrarianList());
        return "librarians";
    }

    @RequestMapping(value = "/librarians", method = RequestMethod.POST)
    public String addLibrarian(@RequestParam("username") String username, @RequestParam("password") String password, ModelMap modelMap){
        userService.addNewLibrarian(username,password);
        modelMap.addAttribute("librarians", userService.getLibrarianList());
        return "librarians";
    }

    @RequestMapping(path = "/librarians/del/{id}")
    public String deleteLibrarian(@PathVariable("id") Long id, ModelMap modelMap){
        userService.removeUser(id);
        modelMap.addAttribute("librarians", userService.getLibrarianList());
        return "librarians";
    }

}
