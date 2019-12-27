package org.codnect.validator.expression;

import org.codnect.validator.base.TestContext;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Burak Köken on 27.12.2019.
 */
public class BooleanExpressionEvaluatorTest extends TestContext {

    @Test
    public void givenAnyExpression_WhenEvaluateIsCalled_ThenShouldEvaluateToTrue() {
        EvaluationContext evaluationContext = mock(EvaluationContext.class);
        Expression expression = mock(Expression.class);
        when(expression.getValue(evaluationContext, Boolean.class))
                .thenReturn(Boolean.TRUE);
        BooleanExpressionEvaluator evaluator = new BooleanExpressionEvaluator(evaluationContext);
        assertEquals(Boolean.TRUE, evaluator.evaluate(expression));
        verify(expression, times(1)).getValue(evaluationContext, Boolean.class);
    }

}