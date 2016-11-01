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
 * Created by Joost on 1-11-2016.
 */
@Controller
@RequestMapping("/expired")
public class ExpiredController {
    @RequestMapping(method = RequestMethod.GET)
    public String returnView(Model model) {
        model.addAttribute("login", new User());
        return "expired";
    }
}