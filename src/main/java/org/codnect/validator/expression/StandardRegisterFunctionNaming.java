package org.codnect.validator.expression;

import java.lang.reflect.Method;

/**
 * Created by Burak Köken on 27.12.2019.
 */
public class StandardRegisterFunctionNaming implements RegisterFunctionNaming {

    @Override
    public String getFunctionName(Class classZ, Method method) {
        return method.getName();
    }

}

