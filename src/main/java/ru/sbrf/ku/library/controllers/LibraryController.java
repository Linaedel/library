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
        personService.removePerson(id);
        modelMap.addAttribute("librarians", personService.getLibrarianList());
        return "librarians";
    }

    @RequestMapping(value = "/readers", method = RequestMethod.GET)
    public String clientList(ModelMap modelMap) {
        modelMap.addAttribute("readers", personService.getClientList());
        return "readers";
    }

    @RequestMapping(value = "/reader", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String book(ModelMap modelMap ) {
        modelMap.addAttribute( "reader", new Person() );
        return "reader";
    }

    @RequestMapping(value = "/reader", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String bookEdit(@ModelAttribute("reader") Person person, ModelMap modelMap ) {
        personService.addOrUpdate(person);
        modelMap.addAttribute( "readers", personService.getClientList() );
        return "readers";
    }

    @RequestMapping(value = "/reader/{id}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String book(@PathVariable("id") Long id, ModelMap modelMap ) {
        modelMap.addAttribute( "reader", personService.getPerson( id ) );
        return "reader";
    }

    @RequestMapping(path = "/readers/del/{id}")
    public String deleteReader(@PathVariable("id") Long id, ModelMap modelMap){
        personService.removePerson(id);
        modelMap.addAttribute("readers", personService.getClientList());
        return "readers";
    }

    @RequestMapping(value = {"/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }

}
