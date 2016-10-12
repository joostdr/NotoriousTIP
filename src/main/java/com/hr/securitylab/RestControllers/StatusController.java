package com.hr.securitylab.RestControllers;

import com.hr.securitylab.Models.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by joost on 4-10-2016.
 */
@RestController
@RequestMapping("/test")
public class StatusController {
    @RequestMapping(method = RequestMethod.GET)
    public Test greeting() {
        return new Test("Hello");
    }
}
