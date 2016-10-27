package com.hr.securitylab.controllers.restcontrollers;

import com.hr.securitylab.database.models.entities.Response;
import com.hr.securitylab.services.EncryptionService;
import com.hr.securitylab.services.HttpService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by joost on 4-10-2016.
 */
@RestController
public class MainframeController {
    private HttpService http;
    private EncryptionService encryptionService;
    public MainframeController() {
        this.http = new HttpService();
        this.encryptionService = new EncryptionService();
    }

    @RequestMapping(value = "/on", method = RequestMethod.GET)
    public String turnOn() throws Exception{
        return http.httpOn();
    }

    @RequestMapping(value = "/off", method = RequestMethod.GET)
    public String turnOff() throws Exception{
        return http.httpOff();
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test(HttpServletResponse response) throws Exception{
        return response.getHeader("Authorization");
    }

    @RequestMapping(value = "/api/decrypt", method = RequestMethod.POST)
    public String decryptCiphertext(HttpServletRequest request) throws Exception{
        return request.getHeader("Authorization");
    }

    @RequestMapping(value = "/api/setkey", method = RequestMethod.POST)
    public Response retrieveDeviceEncryptionKey(HttpServletRequest request){
        if(encryptionService.checkIfProductIdIsValid(request.getHeader("productid"))){
            return encryptionService.getKey(request);
        }
        else{
            return new Response(400, "productid not valid");
        }
    }

}
