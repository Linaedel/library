package ru.sbrf.ku.library.services;

import org.springframework.web.servlet.ModelAndView;

public interface ViewService {
    ModelAndView getAvailableBooks(String resource);
}
