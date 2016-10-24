package com.hr.securitylab.services;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by joost on 24-10-2016.
 */
public class logtest {

    final static Logger logger = Logger.getLogger(logtest.class);

    public logtest() {
        System.out.println("Constructor");
    }

    private void logError() {
        logger.error("Ayyy");
    }

    public static void main(String[] args) {
        logtest logtest = new logtest();
        logtest.logError();
    }

}
