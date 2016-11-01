package com.hr.securitylab.webcontrollers;

import com.hr.securitylab.database.DatabaseFactory;
import com.hr.securitylab.database.entities.rest.Response;
import com.hr.securitylab.services.PollingService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Main controller, serves the main page upon a GET to '/main'
 */

@Controller
@RequestMapping("/main")
public class MainController {

    private PollingService pollingService;
    public MainController() {
        pollingService = new PollingService();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String returnView() {
        return "main";
    }

    @RequestMapping(method = RequestMethod.POST)
    public void vibrate(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        pollingService.updateVibrateColumnForUser(DatabaseFactory.getUserService().findUserByUsername(auth.getName()));
    }
}



