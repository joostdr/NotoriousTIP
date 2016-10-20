package com.hr.securitylab.webcontrollers;

import com.hr.securitylab.database.models.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by joost on 4-10-2016.
 */

@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping(method = RequestMethod.GET)
    public String returnView(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("login", new User());
        return "login";
    }
}
