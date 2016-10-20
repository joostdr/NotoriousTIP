package com.hr.securitylab.webcontrollers;

import com.hr.securitylab.database.models.entities.ResetPassword;
import com.hr.securitylab.services.ResetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by joost on 4-10-2016.
 */

@Controller
@RequestMapping("/resetpassword")
public class ResetPasswordController{
    @RequestMapping(method = RequestMethod.GET)
    public String returnView(Model model) {
        model.addAttribute("resetpassword", new ResetPassword());
        return "resetpassword";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String resetpassword(@ModelAttribute("resetpassword") @Valid ResetPassword resetPassword, Errors errors) {
        ResetService resetService = new ResetService();
        if(!errors.hasErrors()){
            resetService.updatePassword(resetPassword);
            return "redirect:/login.html";
        }
        return "resetpassword";
    }

}
