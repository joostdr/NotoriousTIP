package com.hr.securitylab.RestControllers;

import com.hr.securitylab.Models.Login;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by joost on 4-10-2016.
 */
@RestController
@RequestMapping("/test")
public class CommandController {
    @RequestMapping(method = RequestMethod.GET)
    public Login greeting() {
        //return new Login("Hello");
        return null;
    }
}
