package com.hr.securitylab.validation.classes;

import com.hr.securitylab.database.DatabaseFactory;
import com.hr.securitylab.validation.annotations.EmailNotInUse;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Joost on 17-10-2016.
 */
public class EmailNotInUseValidator implements ConstraintValidator<EmailNotInUse, String> {
    @Override
    public void initialize(EmailNotInUse constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !DatabaseFactory.getUserService().checkIfEmailExists(email); //! because the exception is thrown when the emailexists > when result is false (ofzo)
    }
}
