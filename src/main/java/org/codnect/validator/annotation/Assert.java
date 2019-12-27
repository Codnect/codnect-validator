package org.codnect.validator.annotation;

import org.codnect.validator.AssertValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by Burak Köken on 27.12.2019.
 */
@Target({
        ElementType.METHOD,
        ElementType.FIELD,
        ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,
        ElementType.PARAMETER,
        ElementType.TYPE_USE
})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Assert.List.class)
@Constraint(validatedBy = {AssertValidator.class})
@Documented
public @interface Assert {

    String message() default "{org.codnect.validator.annotation.Assert.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String value() default "";

    String applyIf() default "";

    Class<?>[] assertHelpers() default {};

    @Target({ElementType.METHOD,
            ElementType.FIELD,
            ElementType.ANNOTATION_TYPE,
            ElementType.CONSTRUCTOR,
            ElementType.PARAMETER,
            ElementType.TYPE_USE
    })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        Assert[] value();
    }

}

