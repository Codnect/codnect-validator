package org.codnect.validator;

import org.codnect.validator.annotation.Assert;
import org.codnect.validator.expression.ExpressionValidator;

import javax.validation.ConstraintValidatorContext;

/**
 * Created by Burak Köken on 27.12.2019.
 */
public class AssertValidator extends ExpressionValidator<Assert,Object> {

    @Override
    public void initialize(Assert constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }

}

