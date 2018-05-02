package com.fiipractic.health.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author stefan (Stefan.Opariuc@tss-yonder.com)
 * @since 29/04/2018
 **/
public class EmailValidator implements ConstraintValidator<EmailAnnotation, String> {

    @Override
    public void initialize(EmailAnnotation constraintAnnotation) {
        System.out.println("Here we create some context :)");
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.contains("@");
    }
}
