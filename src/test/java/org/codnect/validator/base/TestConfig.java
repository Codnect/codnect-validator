package org.codnect.validator.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ValidatorFactory;

/**
 * Created by Burak Köken on 27.12.2019.
 */
@Configuration
public class TestConfig {

    @Bean
    public ValidatorFactory validatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public TestBean testBean() {
        return new TestBean();
    }

}