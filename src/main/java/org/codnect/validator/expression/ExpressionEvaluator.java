package org.codnect.validator.expression;

import org.springframework.expression.Expression;

/**
 * Created by Burak Köken on 27.12.2019.
 */
public interface ExpressionEvaluator<T> {

    T evaluate(Expression expression);

}
