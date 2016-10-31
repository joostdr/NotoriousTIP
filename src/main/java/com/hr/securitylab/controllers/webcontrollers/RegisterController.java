package com.hr.securitylab.webcontrollers;

import com.hr.securitylab.database.models.entities.NewUser;
import com.hr.securitylab.services.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Register controller, serves the main page upon a GET to '/register'
 * POST to /register is used for registering a new user
 */

@Controller
@RequestMapping("/register")
public class RegisterController{

    @RequestMapping(method = RequestMethod.GET)
    public String returnView(Model model) {
        model.addAttribute("newuser", new NewUser());
        return "register";
    }

    /**
     * Registers a new user based on the supplied NewUser object
     * Checks if there are validation errors > see services/validation for more
     * @param newUser
     * @param errors
     * @return
     */

    @RequestMapping(method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("newuser") @Valid NewUser newUser, Errors errors) {
        RegisterService registerService = new RegisterService();
        if(!errors.hasErrors()){
            registerService.createNewAccount(newUser);
            return "redirect:/login.html";
        }
        return "register";
    }


}
