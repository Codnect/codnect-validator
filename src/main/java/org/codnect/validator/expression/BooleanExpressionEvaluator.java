package org.codnect.validator.expression;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;

/**
 * Created by Burak Köken on 27.12.2019.
 */
public class BooleanExpressionEvaluator extends AbstractExpressionEvaluator<Boolean> {

    public BooleanExpressionEvaluator(EvaluationContext evaluationContext) {
        super(evaluationContext);
    }

    @Override
    public Boolean evaluate(Expression expression) {
        Boolean result = expression.getValue(getEvaluationContext(), Boolean.class);
        return result == null ? false : result;
    }

}

