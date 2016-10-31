package com.hr.securitylab.validation.classes;

import com.hr.securitylab.database.DatabaseFactory;
import com.hr.securitylab.database.entities.hibernate.Product;
import com.hr.securitylab.validation.annotations.ProductIdExists;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

/**
 * Created by joost on 31-10-2016.
 */
public class ProductIdExistsValidator implements ConstraintValidator<ProductIdExists, String> {
    @Override
    public void initialize(ProductIdExists constraintAnnotation) {

    }

    @Override
    public boolean isValid(String productId, ConstraintValidatorContext context) {
        Optional<Product> result = Optional.ofNullable((Product)DatabaseFactory.getProductService().findById(productId));
        return result.isPresent();
    }
}
