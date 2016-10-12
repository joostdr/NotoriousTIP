package com.hr.securitylab.WebControllers;

import com.hr.securitylab.Models.Login;
import com.hr.securitylab.Services.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by joost on 4-10-2016.
 */

@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping(method = RequestMethod.GET)
    public String returnView(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("login", new Login());
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String test(@ModelAttribute Login login) {
        return LoginService.loginUser(login);
    }
}
