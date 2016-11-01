package com.hr.securitylab.services;

import com.hr.securitylab.database.DatabaseFactory;
import com.hr.securitylab.database.entities.hibernate.Polling;
import com.hr.securitylab.database.entities.hibernate.Product;
import com.hr.securitylab.database.entities.hibernate.User;
import com.hr.securitylab.database.entities.validation.NewUser;
import org.springframework.validation.Errors;

import java.util.Date;

/**
 * Class which is used for registering a new user
 * NewUser object is supplied and gets mapped to an user object
 * The final user object gets saved in the hibernate
 */
public class RegisterService {

    public void createNewAccount(NewUser newUser){
        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(PasswordService.encryptPassword(newUser.getPassword()));
        user.setCreated_at(new Date());

        Product product = DatabaseFactory.getProductService().findProductByProductCode(newUser.getProductCode());
        product.setUser(user);
        product.setActivated(true);
        product.setUpdated_at(new Date());
        user.setProduct(product);

        Polling polling = new Polling();
        polling.setVibrate(false);
        polling.setHasUpdate(false);
        polling.setProduct(product);
        product.setPolling(polling);


        DatabaseFactory.getProductService().saveOrUpdate(product);
        DatabaseFactory.getPollingService().saveOrUpdate(polling);
        DatabaseFactory.getUserService().saveOrUpdate(user);
    }
}
