package com.hr.securitylab.services.validation.annotations;

import com.hr.securitylab.services.validation.classes.ProductCodeNotInUseValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ProductCodeNotInUseValidator.class)
@Documented
public @interface ProductCodeNotInUse {
    String message() default "Productcode already in use";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
