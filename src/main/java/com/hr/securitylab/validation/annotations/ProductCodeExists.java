package com.hr.securitylab.validation.annotations;

import com.hr.securitylab.validation.classes.ProductCodeExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ProductCodeExistsValidator.class)
@Documented
public @interface ProductCodeExists {
    String message() default "Invalid productcode";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
