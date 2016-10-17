package com.hr.securitylab.services.validation;

import com.hr.securitylab.database.models.DatabaseFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Joost on 17-10-2016.
 */
public class CustomEmailNotInUseValidator implements ConstraintValidator<EmailNotInUse, String> {
    @Override
    public void initialize(EmailNotInUse constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !DatabaseFactory.getUserService().checkIfEmailExists(email); //! because the exception is thrown when the emailexists > when result is false (ofzo)
    }
}
