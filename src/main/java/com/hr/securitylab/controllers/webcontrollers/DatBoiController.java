package com.hr.securitylab.controllers.webcontrollers;

import com.hr.securitylab.database.models.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Joost on 29-10-2016.
 */

@Controller
@RequestMapping("/datboi")
public class DatBoiController {
    @RequestMapping(method = RequestMethod.GET)
    public String returnView(Model model) {
        model.addAttribute("login", new User());
        return "datboi";
    }
}
