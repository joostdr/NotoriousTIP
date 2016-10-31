package com.hr.securitylab.database;

import com.hr.securitylab.database.dao.polling.PollingService;
import com.hr.securitylab.database.dao.polling.PollingServiceImpl;
import com.hr.securitylab.database.dao.product.ProductService;
import com.hr.securitylab.database.dao.product.ProductServiceImpl;
import com.hr.securitylab.database.dao.user.UserService;
import com.hr.securitylab.database.dao.user.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Class used for instantiating DaoServiceImplementations based on the autowired spring beans > see spring.xml
 * This way we don't need to define an applicationcontext everytime we need to instantiate a serviceimpl
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

    public static ProductService getProductService(){
        return (ProductServiceImpl) context.getBean("productServiceImpl");
    }

    public static PollingService getPollingService(){
        return (PollingServiceImpl) context.getBean("pollingServiceImpl");
    }
}
