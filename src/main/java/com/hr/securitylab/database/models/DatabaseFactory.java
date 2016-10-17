package com.hr.securitylab.database.models;

import com.hr.securitylab.database.models.dao.UserService;
import com.hr.securitylab.database.models.dao.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Joost on 17-10-2016.
 */
public class DatabaseFactory {

    private static final ApplicationContext context;

    private DatabaseFactory() {
    }

    static{
        context = new ClassPathXmlApplicationContext("spring.xml");
    }

    public static UserService getUserService(){
        return (UserServiceImpl) context.getBean("userServiceImpl");
    }
}
