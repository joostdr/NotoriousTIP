package com.hr.securitylab.database.models.dao;

import com.hr.securitylab.database.models.entities.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Joost on 14-10-2016.
 */
public class test {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        UserServiceImpl userService = (UserServiceImpl) context.getBean("userServiceImpl");
        User user = userService.findByUsername("Frans");
    }

}
