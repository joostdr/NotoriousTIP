package com.hr.securitylab.restcontrollers;

import com.hr.securitylab.services.HttpService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by joost on 4-10-2016.
 */
@RestController
@RequestMapping("/test")
public class CommandController {
    private HttpService http;
    public CommandController() {
        this.http = new HttpService();
    }

    @RequestMapping(value = "/on", method = RequestMethod.GET)
    public void turnOn() throws Exception{
        http.httpOn();
    }

    @RequestMapping(value = "/off", method = RequestMethod.GET)
    public void turnOff() throws Exception{
        http.httpOff();
    }

}
