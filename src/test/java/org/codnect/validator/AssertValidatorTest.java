package org.codnect.validator;

import org.codnect.validator.annotation.Assert;
import org.codnect.validator.base.TestContext;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.reflect.Field;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.*;

/**
 * Created by Burak Köken on 27.12.2019.
 */
@PrepareForTest({AssertValidator.class})
public class AssertValidatorTest extends TestContext {

    class TestAssertAnnotationModel {
        @Assert("#this == null")
        String testField;
        @Assert(value = "'hello'.equals(#this)", applyIf = "#this != null")
        String testFieldForApplyIf;
    }

    @Test
    public void givenAnyAssertAnnotationWithExpression_whenInitializeMethodIsCalled_ThenShouldParseExpressions() throws Exception {
        ExpressionParser expressionParser = mock(SpelExpressionParser.class);
        whenNew(SpelExpressionParser.class)
                .withNoArguments()
                .thenReturn((SpelExpressionParser) expressionParser);

        Assert testFieldAssertAnnotation = getAssertAnnotationByFieldName("testField");
        AssertValidator assertValidator = new AssertValidator();
        assertValidator.initialize(testFieldAssertAnnotation);

        verify(expressionParser, times(1)).parseExpression(anyString());
        verify(expressionParser).parseExpression(testFieldAssertAnnotation.value());
    }

    @Test
    public void givenAnyAssertAnnotationWithExpressionAndApplyIfExpression_whenInitializeMethodIsCalled_ThenShouldParseExpressions() throws Exception {
        ExpressionParser expressionParser = Mockito.mock(SpelExpressionParser.class);
        whenNew(SpelExpressionParser.class)
                .withNoArguments()
                .thenReturn((SpelExpressionParser) expressionParser);

        Assert testFieldAssertAnnotationWithApplyIf = getAssertAnnotationByFieldName("testFieldForApplyIf");
        AssertValidator assertValidator = new AssertValidator();
        assertValidator.initialize(testFieldAssertAnnotationWithApplyIf);

        verify(expressionParser, times(2)).parseExpression(anyString());
        verify(expressionParser).parseExpression(testFieldAssertAnnotationWithApplyIf.value());
        verify(expressionParser).parseExpression(testFieldAssertAnnotationWithApplyIf.applyIf());
    }

    private Field getFieldByName(String fieldName) throws NoSuchFieldException {
        return TestAssertAnnotationModel.class.getDeclaredField(fieldName);
    }

    private Assert getAssertAnnotationByFieldName(String fieldName) throws NoSuchFieldException {
        return getFieldByName(fieldName).getAnnotation(Assert.class);
    }

}
