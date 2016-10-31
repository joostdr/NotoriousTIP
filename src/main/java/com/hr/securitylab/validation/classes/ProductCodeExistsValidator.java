package com.hr.securitylab.validation.classes;

import com.hr.securitylab.database.DatabaseFactory;
import com.hr.securitylab.validation.annotations.ProductCodeExists;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductCodeExistsValidator implements ConstraintValidator<ProductCodeExists, String>
{
    @Override
    public void initialize(ProductCodeExists constraintAnnotation) {

    }

    @Override
    public boolean isValid(String productCode, ConstraintValidatorContext context) {
        return DatabaseFactory.getProductService().checkIfProductCodeExists(productCode);
    }
}
