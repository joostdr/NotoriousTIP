package com.hr.securitylab.services;

import com.hr.securitylab.database.DatabaseFactory;
import com.hr.securitylab.database.entities.hibernate.User;
import com.hr.securitylab.database.entities.validation.ResetPassword;

import java.util.Date;

/**
 * Class which is used for resetting the password of an user
 * Resetpassword gets supplied and mapped to a user model
 * The final user object gets updated in the hibernate
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
