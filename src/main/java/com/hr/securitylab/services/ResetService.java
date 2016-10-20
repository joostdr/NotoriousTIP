package com.hr.securitylab.services;

import com.hr.securitylab.database.models.DatabaseFactory;
import com.hr.securitylab.database.models.entities.ResetPassword;
import com.hr.securitylab.database.models.entities.User;

import java.util.Date;

public class ResetService {

    public ResetService() {
    }

    public void updatePassword(ResetPassword resetPassword){
        User user = DatabaseFactory.getProductService().findProductByProductCode(resetPassword.getProductCode()).getUser();
        user.setPassword(resetPassword.getNewPassword());
        user.setUpdated_at(new Date());
        DatabaseFactory.getUserService().saveOrUpdate(user);
    }
}
