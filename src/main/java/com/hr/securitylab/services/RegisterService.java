package com.hr.securitylab.services;

import com.hr.securitylab.database.models.DatabaseFactory;
import com.hr.securitylab.database.models.entities.NewUser;
import com.hr.securitylab.database.models.entities.Product;
import com.hr.securitylab.database.models.entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by Joost on 16-10-2016.
 */
public class RegisterService {

    public void showErrors(Errors errors){
        System.out.println(errors.getGlobalErrors());
    }

    public void createNewAccount(NewUser newUser){
        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(encryptPassword(newUser.getPassword()));
        user.setCreated_at(new Date());
        Product product = DatabaseFactory.getProductService().findProductByProductCode(newUser.getProductCode());
        product.setUser(user);
        product.setActivated(true);
        product.setUpdated_at(new Date());
        user.setProducts(new HashSet<>(Arrays.asList(product)));
        DatabaseFactory.getUserService().saveOrUpdate(user);
        DatabaseFactory.getProductService().saveOrUpdate(product);
    }

    private String encryptPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

}
