package org.codnect.validator.expression;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.BeanResolver;

import javax.validation.ConstraintValidator;
import java.lang.annotation.Annotation;

/**
 * Created by Burak Köken on 27.12.2019.
 */
public abstract class ExpressionValidator<A extends Annotation, T> implements ConstraintValidator<A, T>, BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public final BeanResolver getBeanResolver() {
        return new BeanFactoryResolver(beanFactory);
    }

}