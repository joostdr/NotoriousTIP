package com.hr.securitylab.services;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joost on 29-10-2016.
 */
public class HttpServiceTest {
    HttpService httpService;

    @Before
    public void prepare(){
        httpService = new HttpService();
    }
    @Test
    public void httpOn() throws Exception {
        httpService.httpOn();
    }

    @Test
    public void httpOff() throws Exception {

    }

}