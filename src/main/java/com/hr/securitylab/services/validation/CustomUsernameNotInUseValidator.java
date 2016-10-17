package com.hr.securitylab.services.validation;

import com.hr.securitylab.database.models.DatabaseFactory;
import org.springframework.validation.beanvalidation.CustomValidatorBean;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Joost on 17-10-2016.
 */
public class CustomUsernameNotInUseValidator implements ConstraintValidator<UsernameNotInUse, String> {
    @Override
    public void initialize(UsernameNotInUse constraintAnnotation) {

    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return !DatabaseFactory.getUserService().checkIfUsernameExists(username);
    }
}
