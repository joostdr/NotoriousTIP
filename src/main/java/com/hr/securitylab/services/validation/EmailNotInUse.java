package com.hr.securitylab.services.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Joost on 17-10-2016.
 */
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CustomEmailNotInUseValidator.class)
@Documented
public @interface EmailNotInUse {

    String message() default "Email already in use";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}
