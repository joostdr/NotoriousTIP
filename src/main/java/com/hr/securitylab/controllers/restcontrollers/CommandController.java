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

    @RequestMapping(value = "/on", method = RequestMethod.GET)
    public void turnOn() throws Exception{
        HttpService http = new HttpService();
        http.httpOn();
    }

    @RequestMapping(value = "/off", method = RequestMethod.GET)
    public void turnOff() throws Exception{
        HttpService http = new HttpService();
        http.httpOff();
    }

}
