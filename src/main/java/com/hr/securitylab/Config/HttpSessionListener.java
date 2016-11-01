package com.hr.securitylab.config;

import javax.servlet.http.HttpSessionEvent;

/**
 * Created by Joost on 1-11-2016.
 */
public class HttpSessionListener implements javax.servlet.http.HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setMaxInactiveInterval(10);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
