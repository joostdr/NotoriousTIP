package com.hr.securitylab.services;

import com.hr.securitylab.database.models.DatabaseFactory;
import com.hr.securitylab.database.models.entities.NewUser;
import com.hr.securitylab.database.models.entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;

import java.util.Date;

/**
 * Created by Joost on 16-10-2016.
 */
public class RegisterService {

    public void showErrors(Errors errors){

    }

    public void createNewAccount(NewUser newUser){
        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(encryptPassword(newUser.getPassword()));
        user.setCreated_at(new Date());
        DatabaseFactory.getUserService().saveOrUpdate(user);
    }

    private String encryptPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

}
