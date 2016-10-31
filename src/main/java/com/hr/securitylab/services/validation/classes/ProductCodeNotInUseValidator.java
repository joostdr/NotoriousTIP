package com.hr.securitylab.services.validation.classes;

import com.hr.securitylab.database.DatabaseFactory;
import com.hr.securitylab.services.validation.annotations.ProductCodeNotInUse;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductCodeNotInUseValidator implements ConstraintValidator<ProductCodeNotInUse, String> {
    @Override
    public void initialize(ProductCodeNotInUse constraintAnnotation) {

    }

    @Override
    public boolean isValid(String productCode, ConstraintValidatorContext context) {
        return !DatabaseFactory.getProductService().checkIfProductIsInUse(productCode);
    }
}
