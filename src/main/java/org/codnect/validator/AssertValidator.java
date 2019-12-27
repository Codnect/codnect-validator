package org.codnect.validator;

import org.codnect.validator.annotation.Assert;
import org.codnect.validator.expression.ExpressionValidator;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Burak Köken on 27.12.2019.
 */
public class AssertValidator extends ExpressionValidator<Assert,Object> {

    private Expression expression;
    private Expression applyIfExpression;
    private Set<Class> assertHelpers;

    @Override
    public void initialize(Assert constraintAnnotation) {
        ExpressionParser parser = new SpelExpressionParser();
        expression = parser.parseExpression(constraintAnnotation.value());
        if(StringUtils.hasText(constraintAnnotation.applyIf())) {
            applyIfExpression = parser.parseExpression(constraintAnnotation.applyIf());
        }
        assertHelpers = Arrays.stream(constraintAnnotation.assertHelpers()).collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }

}

