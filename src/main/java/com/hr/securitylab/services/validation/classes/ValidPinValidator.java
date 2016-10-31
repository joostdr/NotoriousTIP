package com.hr.securitylab.services.validation.classes;

import com.hr.securitylab.database.models.DatabaseFactory;
import com.hr.securitylab.database.models.entities.ResetPassword;
import com.hr.securitylab.services.validation.annotations.ValidPin;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidPinValidator implements ConstraintValidator<ValidPin, Object> {

    @Override
    public void initialize(ValidPin constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        ResetPassword resetPassword = (ResetPassword) object;
        return DatabaseFactory.getProductService().checkIfPinIsValid(resetPassword.getProductCode(),resetPassword.getPin());
    }
}
