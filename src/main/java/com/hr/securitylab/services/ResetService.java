package com.hr.securitylab.services;

import com.hr.securitylab.database.models.DatabaseFactory;
import com.hr.securitylab.database.models.entities.ResetPassword;
import com.hr.securitylab.database.models.entities.User;

import java.util.Date;

/**
 * Class which is used for resetting the password of an user
 * Resetpassword gets supplied and mapped to a user model
 * The final user object gets updated in the database
 */
public class ResetService {

    public ResetService() {
    }

    public void updatePassword(ResetPassword resetPassword){
        User user = DatabaseFactory.getProductService().findProductByProductCode(resetPassword.getProductCode()).getUser();
        user.setPassword(PasswordService.encryptPassword(resetPassword.getNewPassword()));
        user.setUpdated_at(new Date());
        DatabaseFactory.getUserService().saveOrUpdate(user);
    }
}
