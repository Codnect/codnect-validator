package org.codnect.validator.expression;

import org.springframework.expression.EvaluationContext;

/**
 * Created by Burak Köken on 27.12.2019.
 */
public abstract class AbstractExpressionEvaluator<T> implements ExpressionEvaluator<T> {

    private EvaluationContext evaluationContext;

    public AbstractExpressionEvaluator(EvaluationContext evaluationContext) {
        this.evaluationContext = evaluationContext;
    }

    protected EvaluationContext getEvaluationContext() {
        return evaluationContext;
    }

    protected void setEvaluationContext(EvaluationContext evaluationContext) {
        this.evaluationContext = evaluationContext;
    }

}

