package ru.sbrf.ku.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.sbrf.ku.library.dao.BookDao;
import ru.sbrf.ku.library.entities.Person;
import ru.sbrf.ku.library.services.PersonService;

@Controller
public class LibraryController {
    @Autowired
    BookDao bookDao;

    @Autowired
    PersonService personService;

    @RequestMapping(value = {"/", "/index"})
    public String index(ModelMap modelMap)  {
        return "index";
    }

    @RequestMapping(value = "/init")
    public String dbInit(ModelMap modelMap) {
        bookDao.list().forEach(book -> System.out.println(book.getDescription().getName()));
        return "index";
    }

    @RequestMapping(value = "/librarians", method = RequestMethod.GET)
    public String librarianList(ModelMap modelMap) {
        modelMap.addAttribute("librarians", personService.getLibrarianList());
        return "librarians";
    }

    @RequestMapping(value = "/librarians", method = RequestMethod.POST)
    public String addLibrarian(@RequestParam("username") String username, @RequestParam("password") String password, ModelMap modelMap){
        personService.addNewLibrarian(username,password);
        modelMap.addAttribute("librarians", personService.getLibrarianList());
        return "librarians";
    }


    @RequestMapping(path = "/librarians/del/{id}")
    public String deleteLibrarian(@PathVariable("id") Long id, ModelMap modelMap){
        personService.removeUser(id);
        modelMap.addAttribute("librarians", personService.getLibrarianList());
        return "librarians";
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public String clientList(ModelMap modelMap) {
        modelMap.addAttribute("clients", personService.getClientList());
        return "clients";
    }

    @RequestMapping(value = "/client", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String book(ModelMap modelMap ) {
        modelMap.addAttribute( "client", new Person() );
        return "client";
    }

    @RequestMapping(value = "/client", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String bookEdit(@ModelAttribute("client") Person person, ModelMap modelMap ) {
        personService.addNewClient(person);
        modelMap.addAttribute( "clients", personService.getClientList() );
        return "clients";
    }

    @RequestMapping(value = {"/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }

}
