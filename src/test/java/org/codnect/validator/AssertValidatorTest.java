package org.codnect.validator;

import org.codnect.validator.annotation.Assert;
import org.codnect.validator.base.TestAssertHelpers;
import org.codnect.validator.base.TestBean;
import org.codnect.validator.base.TestContext;
import org.codnect.validator.expression.BooleanTypeConverter;
import org.codnect.validator.expression.EvaluationContextBuilder;
import org.codnect.validator.expression.StandardRegisterFunctionNaming;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

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

    @Test
    public void givenAnyValue_whenIsValidCalled_ThenShouldReturnTrue() throws Exception {
        AssertValidator validator = spy(new AssertValidator());

        BeanResolver beanResolver = mock(BeanFactoryResolver.class);
        whenNew(BeanFactoryResolver.class)
                .withAnyArguments()
                .thenReturn((BeanFactoryResolver) beanResolver);

        ExpressionParser expressionParser = Mockito.mock(SpelExpressionParser.class);
        whenNew(SpelExpressionParser.class)
                .withNoArguments()
                .thenReturn((SpelExpressionParser) expressionParser);

        Expression expression = mock(Expression.class);
        when(expressionParser.parseExpression(anyString()))
                .thenReturn(expression);

        StandardRegisterFunctionNaming registerFunctionNaming = mock(StandardRegisterFunctionNaming.class);
        whenNew(StandardRegisterFunctionNaming.class)
                .withNoArguments()
                .thenReturn(registerFunctionNaming);

        BooleanTypeConverter typeConverter = mock(BooleanTypeConverter.class);
        whenNew(BooleanTypeConverter.class)
                .withNoArguments()
                .thenReturn(typeConverter);

        Object rootObject = mock(Object.class);

        EvaluationContextBuilder evaluationContextBuilder = mock(EvaluationContextBuilder.class);
        whenNew(EvaluationContextBuilder.class)
                .withNoArguments()
                .thenReturn(evaluationContextBuilder);
        when(evaluationContextBuilder.setRootObject(rootObject))
                .thenReturn(evaluationContextBuilder);
        when(evaluationContextBuilder.setAssertHelpers(anySet()))
                .thenReturn(evaluationContextBuilder);
        when(evaluationContextBuilder.setBeanResolver(beanResolver))
                .thenReturn(evaluationContextBuilder);
        when(evaluationContextBuilder.setRegisterFunctionNaming(registerFunctionNaming))
                .thenReturn(evaluationContextBuilder);
        when(evaluationContextBuilder.setTypeConverter(typeConverter))
                .thenReturn(evaluationContextBuilder);

        EvaluationContext evaluationContext = mock(StandardEvaluationContext.class);
        when(evaluationContextBuilder.build()).thenReturn(evaluationContext);

        Assert testFieldAssertAnnotation = getAssertAnnotationByFieldName("testField");
        validator.initialize(testFieldAssertAnnotation);
        validator.isValid(rootObject, null);

        verify(evaluationContextBuilder).setRootObject(rootObject);
        verify(evaluationContextBuilder).setTypeConverter(typeConverter);
        verify(evaluationContextBuilder).setBeanResolver(beanResolver);
        verify(evaluationContextBuilder).setRegisterFunctionNaming(registerFunctionNaming);
        verify(evaluationContextBuilder).setAssertHelpers(anySet());

        verifyPrivate(validator).invoke("evaluateApplyIfExpression", evaluationContext);
        verifyPrivate(validator).invoke("evaluate", evaluationContext, expression);
    }

    private Field getFieldByName(String fieldName) throws NoSuchFieldException {
        return TestAssertAnnotationModel.class.getDeclaredField(fieldName);
    }

    private Assert getAssertAnnotationByFieldName(String fieldName) throws NoSuchFieldException {
        return getFieldByName(fieldName).getAnnotation(Assert.class);
    }

}
