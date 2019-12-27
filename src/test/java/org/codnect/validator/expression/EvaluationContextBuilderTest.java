package org.codnect.validator.expression;

import org.codnect.validator.base.TestAssertHelpers;
import org.codnect.validator.base.TestContext;
import org.junit.Test;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.TypeConverter;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.*;
import static org.junit.Assert.*;

/**
 * Created by Burak Köken on 27.12.2019.
 */
@PrepareForTest({EvaluationContextBuilder.class})
public class EvaluationContextBuilderTest extends TestContext {

    @Mock
    private Object rootObject;

    @Mock
    private BeanResolver beanResolver;

    @Test
    public void givenAnyEvaluationContextParameter_WhenBuildMethodIsCalled_ThenEverythingShouldBeOk() throws Exception {
        EvaluationContextBuilder evaluationContextBuilder = PowerMockito.spy(new EvaluationContextBuilder());
        whenNew(EvaluationContextBuilder.class)
                .withNoArguments()
                .thenReturn(evaluationContextBuilder);

        StandardEvaluationContext standardEvaluationContext = PowerMockito.spy(new StandardEvaluationContext());
        whenNew(StandardEvaluationContext.class)
                .withNoArguments()
                .thenReturn(standardEvaluationContext);

        TypeConverter typeConverter = mock(TypeConverter.class);
        StandardRegisterFunctionNaming standardRegisterFunctionNaming = new StandardRegisterFunctionNaming();
        Set<Class> assertHelpers = new HashSet<>();
        assertHelpers.add(TestAssertHelpers.class);

        StandardEvaluationContext evaluationContext = (StandardEvaluationContext) new EvaluationContextBuilder()
                .setRootObject(rootObject)
                .setTypeConverter(typeConverter)
                .setRegisterFunctionNaming(standardRegisterFunctionNaming)
                .setBeanResolver(beanResolver)
                .setAssertHelpers(assertHelpers)
                .build();
        assertEquals(standardEvaluationContext, evaluationContext);

        verify(standardEvaluationContext, times(1)).setRootObject(rootObject);
        verify(standardEvaluationContext, times(1)).setTypeConverter(typeConverter);
        verify(standardEvaluationContext, times(1)).setBeanResolver(beanResolver);

        /* verify if the method of registerFunction is called for all asserts helpers */
        verifyPrivate(evaluationContextBuilder, times(1)).invoke("registerFunctions", standardEvaluationContext);
        verify(standardEvaluationContext, times(1)).registerFunction("isNull", getStaticMethodByName("isNull"));
        verify(standardEvaluationContext, times(1)).registerFunction("isNotNull", getStaticMethodByName("isNotNull"));
        verify(standardEvaluationContext, times(1)).registerFunction("isEmpty", getStaticMethodByName("isEmpty"));
        verify(standardEvaluationContext, times(1)).registerFunction("length", getStaticMethodByName("length"));
    }

    private static Method getStaticMethodByName(String name) {
        Method[] methods = TestAssertHelpers.class.getDeclaredMethods();
        return Arrays.stream(methods).filter(method -> method.getName().equals(name))
                .findFirst()
                .get();
    }

}
