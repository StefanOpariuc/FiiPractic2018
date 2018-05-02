package com.fiipractic.health.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author stefan (Stefan.Opariuc@tss-yonder.com)
 * @since 29/04/2018
 **/
@Constraint(validatedBy = {EmailValidator.class})
@Target({METHOD, FIELD, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface EmailAnnotation {
    String message() default "The email format is not correct";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
