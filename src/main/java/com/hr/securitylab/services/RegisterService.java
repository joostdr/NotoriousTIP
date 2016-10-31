package com.hr.securitylab.services;

import com.hr.securitylab.database.DatabaseFactory;
import com.hr.securitylab.database.entities.hibernate.Product;
import com.hr.securitylab.database.entities.hibernate.User;
import com.hr.securitylab.database.entities.validation.NewUser;
import org.springframework.validation.Errors;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

/**
 * Class which is used for registering a new user
 * NewUser object is supplied and gets mapped to an user object
 * The final user object gets saved in the hibernate
 */
public class RegisterService {

    public void showErrors(Errors errors){
        System.out.println(errors.getGlobalErrors());
    }

    public void createNewAccount(NewUser newUser){
        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(PasswordService.encryptPassword(newUser.getPassword()));
        user.setCreated_at(new Date());
        Product product = DatabaseFactory.getProductService().findProductByProductCode(newUser.getProductCode());
        product.setUser(user);
        product.setActivated(true);
        product.setUpdated_at(new Date());
        user.setProducts(new HashSet<>(Arrays.asList(product)));
        DatabaseFactory.getUserService().saveOrUpdate(user);
        DatabaseFactory.getProductService().saveOrUpdate(product);
    }
}
