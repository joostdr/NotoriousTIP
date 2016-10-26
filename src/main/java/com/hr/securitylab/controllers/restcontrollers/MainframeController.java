package com.hr.securitylab.controllers.restcontrollers;

import com.hr.securitylab.services.HttpService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by joost on 4-10-2016.
 */
@RestController
@RequestMapping("/command")
public class MainframeController {
    private HttpService http;
    public MainframeController() {
        this.http = new HttpService();
    }

    @RequestMapping(value = "/on", method = RequestMethod.GET)
    public String turnOn() throws Exception{
        return http.httpOn();
    }

    @RequestMapping(value = "/off", method = RequestMethod.GET)
    public String turnOff() throws Exception{
        return http.httpOff();
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.GET)
    public String storeIP(HttpServletRequest result){
        return result.getRemoteAddr();
    }

}
