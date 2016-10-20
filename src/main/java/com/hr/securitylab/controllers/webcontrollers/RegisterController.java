package com.hr.securitylab.webcontrollers;

import com.hr.securitylab.database.models.entities.NewUser;
import com.hr.securitylab.services.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by joost on 4-10-2016.
 */

@Controller
@RequestMapping("/register")
public class RegisterController{

    @RequestMapping(method = RequestMethod.GET)
    public String greeting(Model model) {
        model.addAttribute("newuser", new NewUser());
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String showGreeting(@ModelAttribute("newuser") @Valid NewUser newUser, Errors errors) {
        RegisterService registerService = new RegisterService();
        if(!errors.hasErrors()){
            registerService.createNewAccount(newUser);
            return "redirect:/login.html";
        }
        return "register";
    }


}
