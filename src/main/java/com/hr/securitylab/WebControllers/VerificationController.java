package com.hr.securitylab.webcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by joost on 4-10-2016.
 */

@Controller
public class VerificationController {
    @RequestMapping("/verification")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        return "verification";
    }
}
