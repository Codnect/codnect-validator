package org.codnect.validator.expression;

import org.codnect.validator.util.ReflectionUtil;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.TypeConverter;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Burak Köken on 27.12.2019.
 */
public class EvaluationContextBuilder {

    private Object rootObject;
    private TypeConverter typeConverter;
    private BeanResolver beanResolver;
    private Set<Class> assertHelpers = new HashSet<>();
    private RegisterFunctionNaming registerFunctionNaming;

    public EvaluationContextBuilder() {
        this.registerFunctionNaming = new StandardRegisterFunctionNaming();
    }

    public EvaluationContextBuilder setRootObject(Object rootObject) {
        this.rootObject = rootObject;
        return this;
    }

    public EvaluationContextBuilder setTypeConverter(TypeConverter typeConverter) {
        this.typeConverter = typeConverter;
        return this;
    }

    public EvaluationContextBuilder setBeanResolver(BeanResolver beanResolver) {
        this.beanResolver = beanResolver;
        return this;
    }

    public EvaluationContextBuilder setAssertHelpers(Set<Class> assertHelpers) {
        this.assertHelpers = assertHelpers;
        return this;
    }

    public EvaluationContextBuilder setRegisterFunctionNaming(RegisterFunctionNaming registerFunctionNaming) {
        this.registerFunctionNaming = registerFunctionNaming;
        return this;
    }

    public EvaluationContext build() {
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setRootObject(rootObject);
        evaluationContext.setTypeConverter(typeConverter);
        evaluationContext.setBeanResolver(beanResolver);
        registerFunctions(evaluationContext);
        return evaluationContext;
    }

    private void registerFunctions(StandardEvaluationContext evaluationContext) {
        if(!assertHelpers.isEmpty()) {
            Set<Method> assertHelperFunctions = assertHelpers.stream()
                    .flatMap(assertHelperClass -> ReflectionUtil.getAllStaticMethods(assertHelperClass).stream())
                    .collect(Collectors.toSet());
            assertHelperFunctions.stream().forEach(
                    (Method function) -> evaluationContext.registerFunction(
                            registerFunctionNaming.getFunctionName(function.getDeclaringClass(), function),
                            function
                    )
            );
        }
    }

}
