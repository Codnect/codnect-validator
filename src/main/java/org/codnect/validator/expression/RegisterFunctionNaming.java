package org.codnect.validator.expression;

import java.lang.reflect.Method;

/**
 * Created by Burak Köken on 27.12.2019.
 */
public interface RegisterFunctionNaming {

    String getFunctionName(Class classZ, Method method);

}