package com.hr.securitylab.controllers.webcontrollers;

import com.hr.securitylab.database.entities.hibernate.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Index controller, serves the index page upon a GET to '/'
 */
    // TODO 1 user can be logged in at 2 places
    // TODO session termination
    // TODO API security with 1 set of credentials and not from DB
@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping(method = RequestMethod.GET)
    public String returnView(Model model) {
        model.addAttribute("login", new User());
        return "index";
    }
}
