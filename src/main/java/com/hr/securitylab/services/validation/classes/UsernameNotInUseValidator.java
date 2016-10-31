package com.hr.securitylab.services.validation.classes;

import com.hr.securitylab.database.DatabaseFactory;
import com.hr.securitylab.services.validation.annotations.UsernameNotInUse;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Joost on 17-10-2016.
 */
public class UsernameNotInUseValidator implements ConstraintValidator<UsernameNotInUse, String> {
    @Override
    public void initialize(UsernameNotInUse constraintAnnotation) {

    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return !DatabaseFactory.getUserService().checkIfUsernameExists(username);
    }
}
