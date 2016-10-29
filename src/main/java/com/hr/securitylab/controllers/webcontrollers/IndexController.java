package com.hr.securitylab.controllers.webcontrollers;

import com.hr.securitylab.database.models.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Index controller, serves the index page upon a GET to '/'
 */

@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping(method = RequestMethod.GET)
    public String returnView(Model model) {
        model.addAttribute("login", new User());
        return "index";
    }
}
