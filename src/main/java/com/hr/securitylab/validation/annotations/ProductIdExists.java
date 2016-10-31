package com.hr.securitylab.validation.annotations;

import com.hr.securitylab.validation.classes.ProductIdExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by joost on 31-10-2016.
 */
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ProductIdExistsValidator.class)
@Documented
public @interface ProductIdExists{

    String message() default "ProductId doesn't exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

